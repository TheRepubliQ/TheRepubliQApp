package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.RequestService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.UserSolicitesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.PropSolicitiesPresenter;
import br.edu.ifsp.tcc.apprepublic.presenter.UserSolicitiesPresenter;
import br.edu.ifsp.tcc.apprepublic.view.adapter.PropSolicitiesAdapter;
import br.edu.ifsp.tcc.apprepublic.view.adapter.UserSolicitesAdapter;
import br.edu.ifsp.tcc.apptherrepubliq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSolicites extends AppCompatActivity implements UserSolicitesMVP.View {

    private UserSolicitiesPresenter presenter;
    private UserSolicitesAdapter mAdapter;
    private List<HomeEntity> solicitesList;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_solicites);
        presenter = new UserSolicitiesPresenter(this, this);

        findById();
        initializeRecyclerView();
        loadDataFromApi();
        setupSearch();

        mAdapter.setPresenter(presenter);
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
        for (HomeEntity entity : solicitesList) {
            if (entity.getDescr().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(entity);
            }
        }
        mAdapter.setHomeList(filteredList);
        mAdapter.notifyDataSetChanged();
    }
    private void initializeRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_service);
        mAdapter = new UserSolicitesAdapter(this, solicitesList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Propriedade Solicitadas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadDataFromApi() {
        RequestService requestService = RESTService.getRequestService();

        // Obtém os Requests com base no User Prop ID igual ao UserId do usuário logado
        Call<List<HomeEntity>> call = requestService.getRequestsByOwnerId(getAuthorizationToken(), getUserId());
        call.enqueue(new Callback<List<HomeEntity>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<HomeEntity>> call, Response<List<HomeEntity>> response) {
                if (response.isSuccessful()) {
                    solicitesList = response.body();
                    mAdapter.setHomeList(solicitesList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    showMessage("Falha ao obter dados da API");
                }
            }

            @Override
            public void onFailure(Call<List<HomeEntity>> call, Throwable t) {
                showMessage("Erro na solicitação da API: " + t.getMessage());
            }
        });
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        return "Bearer " + accessToken;
    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, HomePage.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}