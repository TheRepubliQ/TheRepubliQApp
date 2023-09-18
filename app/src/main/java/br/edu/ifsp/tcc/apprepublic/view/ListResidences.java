package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.mvp.ListResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ListResidencesPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class ListResidences extends AppCompatActivity implements ListResidencesMVP.View {

    private RecyclerView mRecyclerView;
    private FloatingActionButton btnAddResidence;

    private ListResidencesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_residences);
        // Inicialize o objeto presenter aqui
        presenter = new ListResidencesPresenter(this,this);

        findById();
        setListener();
    }


    private void findById() {

        Objects.requireNonNull(getSupportActionBar()).setTitle("Suas Residências");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerview_service);
        btnAddResidence = findViewById(R.id.fab_add_service);

    }

    private void setListener() {

        btnAddResidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no texto "AdicionarResidencia"
                presenter.adcionarResidencia();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String erro_ao_buscar_usuário) {
        Toast.makeText(this, erro_ao_buscar_usuário, Toast.LENGTH_SHORT).show();

    }


}