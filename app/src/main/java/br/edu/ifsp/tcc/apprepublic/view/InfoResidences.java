package br.edu.ifsp.tcc.apprepublic.view;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.InfoResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.InfoResidencePresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class InfoResidences extends AppCompatActivity implements InfoResidencesMVP.View {

    private InfoResidencesMVP.Presenter presenter;

    private TextView textTitulo;
    private TextView textDescricao;
    private TextView textTipo;
    private TextView textPrec;
    private TextView textEndereco;
    private TextView textEmail;
    private TextView textTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_residences);

        presenter = new InfoResidencePresenter(this, this);

        findById();
        loadDataFromApi();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Informações Residenciais");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textTitulo = findViewById(R.id.text_titulo);
        textDescricao = findViewById(R.id.text_descricao);
        textTipo = findViewById(R.id.text_tipo);
        textPrec = findViewById(R.id.text_prec);
        textEndereco = findViewById(R.id.text_endereco);
        textEmail = findViewById(R.id.text_Email);
        textTelefone = findViewById(R.id.text_Tel);
    }

    private void loadDataFromApi() {
        long homeId = getIntent().getLongExtra("home_id", -1);
        presenter.recuperarResidences(homeId);

    }

    @Override
    public void infoHome(HomeEntity homeEntity) {
        textTitulo.setText(homeEntity.getTitulo());
        textDescricao.setText(homeEntity.getDescr());
        textTipo.setText(homeEntity.getTipo().toString());  // Ou qualquer método que converta o enum para String
        textPrec.setText(String.valueOf(homeEntity.getPreco()));  // Convertendo o preço para String
        textEndereco.setText(homeEntity.getEndereco().Forma());
        textEmail.setText(homeEntity.getUser().getEmail());
        textTelefone.setText(homeEntity.getUser().getTelefone());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
