package br.edu.ifsp.tcc.apprepublic.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.home.Tipo;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.HomePageMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.HomePagePresenter;
import br.edu.ifsp.tcc.apprepublic.presenter.ListResidencesPresenter;
import br.edu.ifsp.tcc.apprepublic.security.TokenResponse;
import br.edu.ifsp.tcc.apprepublic.view.adapter.HomePageAdapter;
import br.edu.ifsp.tcc.apptherrepubliq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements HomePageMVP.View {

    private HomePagePresenter presenter;

    private HomePageAdapter mAdapter;
    private List<HomeEntity> homeList;

    private EditText editTextSearch;

    private Spinner spinner;

    private  User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        presenter = new HomePagePresenter(this, this);
        presenter.getUserById(getUserId());
        initializeRecyclerView();
        setupSearch();
    }


    private void initializeRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_service);
        mAdapter = new HomePageAdapter(this, homeList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadDataFromApiWithFilter( String query) {
        HomeService homeService = RESTService.getHomeService();

        Call<List<HomeEntity>> call = homeService.listHomes(getAuthorizationToken());
        call.enqueue(new Callback<List<HomeEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<HomeEntity>> call, @NonNull Response<List<HomeEntity>> response) {
                if (response.isSuccessful()) {
                    homeList = response.body();
                    filterItems(query);  // Filtrar localmente
                } else {
                    showMessage("Falha ao obter dados da API");
                    Log.d("Falha ao obter dados da API", String.valueOf(response.code()));


                }
            }

            @Override
            public void onFailure(Call<List<HomeEntity>> call, Throwable t) {
                showMessage("Erro na solicitação da API: " + t.getMessage());
                Log.d("Erro na solicitação HomeList da API:", t.getMessage());
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filterItems(String query) {
        List<HomeEntity> filteredList = new ArrayList<>();

        for (HomeEntity entity : homeList) {
            if ((entity.getTipo().getDescription().contains(query) ||
                    entity.getTitulo().contains(query)) &&
                    entity.getOfertado()) {
                filteredList.add(entity);
            }
        }


        mAdapter.setHomeList(filteredList);
        mAdapter.notifyDataSetChanged();
    }


    public void findById(User user) {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Bem Vindos " + user.getName());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        populateTipoMoradiaSpinner();
        this.user = user;

    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        // Obtém a referência para a opção de menu que deseja modificar
        MenuItem residencesItem = menu.findItem(R.id.action_list_residences);
        MenuItem propSolicitationItem = menu.findItem(R.id.action_prop_solicition);

        // Define a visibilidade com base na propriedade isProp
        if (user != null) {
            residencesItem.setVisible(user.getIsProp());
            propSolicitationItem.setVisible(user.getIsProp());
        }

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                presenter.changeToEditPerfil();
                return true;
            case R.id.action_logout:
                presenter.logout();
                return true;
            case R.id.action_edit_password:
                presenter.altSenha();
                return true;
            case R.id.action_list_user_solicition:
                presenter.userSolicit();
                return true;
            case R.id.action_list_residences:
                presenter.changeToRegisterResidence();
                return true;
            case R.id.action_prop_solicition:
                presenter.propSolicit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




    private void setupSearch() {
        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Não é necessário implementar isso
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterItems(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Não é necessário implementar isso
            }
        });
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String authorizationHeader = "Bearer " + accessToken;

        Log.d("Authorization Token", accessToken);
        return authorizationHeader;
    }

    private void populateTipoMoradiaSpinner() {
        spinner = findViewById(R.id.spinnerFilter);

        // Crie um ArrayAdapter para preencher o Spinner
        ArrayAdapter<String> tipoMoradiaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        tipoMoradiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adicione os valores do enum Tipo ao adaptador
        for (Tipo tipo : Tipo.values()) {
            tipoMoradiaAdapter.add(tipo.getDescription());
        }

        // Associe o adaptador ao Spinner
        spinner.setAdapter(tipoMoradiaAdapter);

        // Adicione um listener para capturar a seleção do Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtenha o item selecionado usando a posição
                String selectedValue = tipoMoradiaAdapter.getItem(position);

                Log.d("Spinner Selection", "Position: " + position + ", Value: " + selectedValue);
                loadDataFromApiWithFilter(selectedValue);

                // Aqui você pode usar a posição e o valor conforme necessário
                // Por exemplo, você pode chamar loadDataFromApiWithFilter(position, selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }



}