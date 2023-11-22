package br.edu.ifsp.tcc.apprepublic.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.ListResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ListResidencesPresenter;
import br.edu.ifsp.tcc.apprepublic.view.adapter.ListResidencesAdapter;
import br.edu.ifsp.tcc.apptherrepubliq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListResidences extends AppCompatActivity implements ListResidencesMVP.View {

    private FloatingActionButton btnAddResidence;
    private ListResidencesPresenter presenter;

    private ListResidencesAdapter mAdapter;
    private List<HomeEntity> homeList;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_residences);
        presenter = new ListResidencesPresenter(this, this);

        findById();
        setListener();
        initializeRecyclerView();
        loadDataFromApi();
        setupSearch();

        mAdapter.setPresenter(presenter);

    }

    private void initializeRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_service);
        mAdapter = new ListResidencesAdapter(this, homeList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Suas Residências");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_service);
        btnAddResidence = findViewById(R.id.fab_add_service);
    }

    private void setListener() {
        btnAddResidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.adcionarResidencia();
            }
        });
    }

    public void loadDataFromApi() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);

        HomeService homeService = RESTService.getHomeService();
        String authorizationHeader = "Bearer " + accessToken;

        Long id = getUserId();

        Call<List<HomeEntity>> call = homeService.listHomesByUserId(authorizationHeader, id);
        call.enqueue(new Callback<List<HomeEntity>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<HomeEntity>> call, @NonNull Response<List<HomeEntity>> response) {
                if (response.isSuccessful()) {
                    homeList = response.body();

                    // Filtra as residências com oferta ativada
                    List<HomeEntity> homesComOferta = new ArrayList<>();
                    List<HomeEntity> homesSemOferta = new ArrayList<>();

                    for (HomeEntity home : homeList) {
                        if (home.getOfertado()) {
                            homesComOferta.add(home);
                        } else {
                            homesSemOferta.add(home);
                        }
                    }

                    // Adiciona primeiro as residências com oferta ativada na lista
                    homesComOferta.addAll(homesSemOferta);
                    homeList = homesComOferta;

                    mAdapter.setHomeList(homeList); // Define os dados no adaptador
                    mAdapter.notifyDataSetChanged();
                } else {
                    showMessage("Falha ao obter dados da API");
                }
            }

            @Override
            public void onFailure(Call<List<HomeEntity>> call, Throwable t) {
                showMessage("Erro na solicitação da API: " + t.getMessage());
                Log.d("Erro na solicitação da API: " , t.getMessage());

            }
        });
    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Cria uma nova instância da HomePage
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);

            // Fecha a atividade atual
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @SuppressLint("NotifyDataSetChanged")
    private void filterItems(String query) {
        List<HomeEntity> filteredList = new ArrayList<>();
        for (HomeEntity entity : homeList) {
            if (entity.getDescr().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(entity);
            }
        }
        mAdapter.setHomeList(filteredList);
        mAdapter.notifyDataSetChanged();
    }
}
