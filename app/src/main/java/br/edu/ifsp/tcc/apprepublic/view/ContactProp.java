package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.ContactPropMVP;
import br.edu.ifsp.tcc.apprepublic.mvp.InfoResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ContactPropPresenter;
import br.edu.ifsp.tcc.apprepublic.presenter.InfoResidencePresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class ContactProp extends AppCompatActivity implements ContactPropMVP.View {

    private ContactPropMVP.Presenter presenter;

    private TextView textEmail;
    private TextView textTel;

    private Button btnContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_prop);

        presenter = new ContactPropPresenter(this, this);

        findById();
        loadDataFromApi();
    }

    private void setListener(HomeEntity homeEntity) {

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.messageProp(homeEntity);
            }
        });
    }

    private void loadDataFromApi() {
        long homeId = getIntent().getLongExtra("home_id", -1);
        presenter.recuperarResidences(homeId);
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dados de Contato");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textEmail = findViewById(R.id.text_email);
        textTel = findViewById(R.id.text_tel);
        btnContact = findViewById(R.id.btnMens);
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
    public void infoContat(HomeEntity homeEntity) {
        textEmail.setText(homeEntity.getUser().getEmail());
        textTel.setText(homeEntity.getUser().getTelefone());

        setListener(homeEntity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // Isso faz com que a ação padrão de voltar seja chamada
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}