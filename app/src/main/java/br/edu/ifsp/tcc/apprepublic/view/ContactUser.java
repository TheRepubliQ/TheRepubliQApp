package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.ContactPropMVP;
import br.edu.ifsp.tcc.apprepublic.mvp.ContactUserMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ContactPropPresenter;
import br.edu.ifsp.tcc.apprepublic.presenter.ContactUserPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class ContactUser extends AppCompatActivity implements ContactUserMVP.View{

    private ContactUserMVP.Presenter presenter;

    private TextView textEmail;
    private TextView textTel;

    private Button btnContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_user);

        presenter = new ContactUserPresenter(this, this);

        findById();
        loadDataFromApi();
    }

    private void loadDataFromApi() {
        long userId = getIntent().getLongExtra("requestId", -1);
        presenter.recuperarUser(userId);
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
    public void infoContat(User user) {
        textEmail.setText(user.getEmail());
        textTel.setText(user.getTelefone());

        setListener(user);

    }

    private void setListener(User user) {

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.messageProp(user);
            }
        });
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