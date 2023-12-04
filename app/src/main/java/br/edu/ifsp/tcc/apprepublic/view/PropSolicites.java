package br.edu.ifsp.tcc.apprepublic.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.Api.RequestService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.PropSolicitesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.PropSolicitiesPresenter;
import br.edu.ifsp.tcc.apprepublic.view.adapter.PropSolicitiesAdapter;
import br.edu.ifsp.tcc.apptherrepubliq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropSolicites extends AppCompatActivity implements PropSolicitesMVP.View {

    private PropSolicitiesPresenter presenter;
    private PropSolicitiesAdapter mAdapter;
    private List<HomeEntity> solicitesList;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prop_solicites);
        presenter = new PropSolicitiesPresenter(this, this);

        findById();
        initializeRecyclerView();
        loadDataFromApi();
        setupSearch();

        mAdapter.setPresenter(presenter);
    }

    private void initializeRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_service);
        mAdapter = new PropSolicitiesAdapter(this, solicitesList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Solicitações de Propriedade");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void loadDataFromApi() {
        RequestService requestService = RESTService.getRequestService();

        // Obtém os Requests com base no User Prop ID igual ao UserId do usuário logado
        Call<List<HomeEntity>> call = requestService.getRequestsByUserPropId(getAuthorizationToken(), getUserId());
        call.enqueue(new Callback<List<HomeEntity>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<HomeEntity>> call, Response<List<HomeEntity>> response) {
                if (response.isSuccessful()) {
                    solicitesList = response.body();
                    mAdapter.setPropSolicitiesList(solicitesList);
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
        mAdapter.setPropSolicitiesList(filteredList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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


}
