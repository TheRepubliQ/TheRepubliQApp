package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Objects;
import java.util.List;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.ListResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ListResidencesPresenter;
import br.edu.ifsp.tcc.apprepublic.view.adapter.HomePageAdapter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_residences);
        presenter = new ListResidencesPresenter(this, this);

        findById();
        setListener();
        initializeRecyclerView();
        loadDataFromApi();
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

    private void loadDataFromApi() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);

        HomeService homeService = RESTService.getHomeService();
        String authorizationHeader = "Bearer " + accessToken;

        Call<List<HomeEntity>> call = homeService.listHomesByUserId(authorizationHeader, getUserId());
        call.enqueue(new Callback<List<HomeEntity>>() {
            @Override
            public void onResponse(Call<List<HomeEntity>> call, Response<List<HomeEntity>> response) {
                if (response.isSuccessful()) {
                    homeList = response.body();
                    mAdapter.setHomeList(homeList); // Defina os dados no adaptador
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

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("user_id", -1); // Retorne -1 se o ID não estiver disponível
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
