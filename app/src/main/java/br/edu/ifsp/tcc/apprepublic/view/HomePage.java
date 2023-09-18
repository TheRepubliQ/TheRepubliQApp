package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.edu.ifsp.tcc.apprepublic.presenter.ListResidencesPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class HomePage extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        findById();
        setListener();
    }

    private void setListener() {
    }

    private void findById() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("The RepupliQ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


    public Context getContext() {
        return this;
    }

    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar o menu na Toolbar
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                // Lógica para abrir a tela de edição de perfil
                Intent editProfileIntent = new Intent(this, RegisterUser.class);
                startActivity(editProfileIntent);
                return true;
            case R.id.action_list_residences:
                // Lógica para abrir a tela de listagem de residências
                Intent listResidencesIntent = new Intent(this, ListResidences.class);
                startActivity(listResidencesIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}