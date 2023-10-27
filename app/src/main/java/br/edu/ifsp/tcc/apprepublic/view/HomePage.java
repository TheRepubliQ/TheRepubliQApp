package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import br.edu.ifsp.tcc.apprepublic.view.adapter.HomePageAdapter;
import br.edu.ifsp.tcc.apptherrepubliq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends AppCompatActivity implements HomePageMVP.View {

    private RecyclerView mRecyclerView;

    private HomePagePresenter presenter;

    private HomePageAdapter mAdapter; // Adicione uma instância do adaptador
    private List<HomeEntity> homeList; // A lista que conterá os dados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        presenter = new HomePagePresenter(this,this);
        mRecyclerView = findViewById(R.id.recyclerview_service);
        mAdapter = new HomePageAdapter(this, homeList);
        mRecyclerView.setAdapter(mAdapter);

        findById();
        setListener();
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        HomeService homeService = RESTService.getHomeService(); // Use RESTService para obter o serviço

        Call<List<HomeEntity>> call = homeService.listHomes();
        call.enqueue(new Callback<List<HomeEntity>>() {
            @Override
            public void onResponse(Call<List<HomeEntity>> call, Response<List<HomeEntity>> response) {
                if (response.isSuccessful()) {
                    homeList = response.body();
                    mAdapter.notifyDataSetChanged(); // Atualize o adaptador com os novos dados
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
    public void showMessage(String mensage) {
        Toast.makeText(this, mensage, Toast.LENGTH_SHORT).show();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar o menu na Toolbar
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