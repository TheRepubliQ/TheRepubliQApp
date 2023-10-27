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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        presenter = new HomePagePresenter(this, this);

        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);

        Log.d("AccessTokenDebug", "AccessToken: " + accessToken);

        findById();
        setListener();
        initializeRecyclerView();
        loadDataFromApi(accessToken);
    }

    private void initializeRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_service);
        mAdapter = new HomePageAdapter(this, homeList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void loadDataFromApi(String accessToken) {
        HomeService homeService = RESTService.getHomeService();
        String authorizationHeader = "Bearer " + accessToken;

        Call<List<HomeEntity>> call = homeService.listHomes(authorizationHeader);
        call.enqueue(new Callback<List<HomeEntity>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<HomeEntity>> call, @NonNull Response<List<HomeEntity>> response) {
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

    private void setListener() {
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Bem Vindos");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                presenter.changeToEditPerfil();
                return true;
            case R.id.action_list_residences:
                presenter.changeToRegisterResidence();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
