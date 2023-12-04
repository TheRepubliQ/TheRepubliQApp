package br.edu.ifsp.tcc.apprepublic.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
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

    private Button btnRequest;

    private Request request;  // Adicionado para armazenar os dados da solicitação

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_residences);

        presenter = new InfoResidencePresenter(this, this);

        request = new Request();  // Inicializa a instância da solicitação

        findById();
        loadDataFromApi();
    }

    private void setListener(HomeEntity homeEntity) {

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request.setUserProp(homeEntity.getUser().getId());
                request.setHomeId(homeEntity.getId());
                request.setUserId(getUserId());

                presenter.contactProp(request);
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

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Informações Residenciais");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textTitulo = findViewById(R.id.text_titulo);
        textDescricao = findViewById(R.id.text_descricao);
        textTipo = findViewById(R.id.text_email);
        textPrec = findViewById(R.id.text_tel);
        textEndereco = findViewById(R.id.text_endereco);
        btnRequest = findViewById(R.id.btnRequest);

    }

    private void loadDataFromApi() {
        long homeId = getIntent().getLongExtra("home_id", -1);
        presenter.recuperarResidences(homeId);

    }

    @Override
    public void infoHome(HomeEntity homeEntity) {
        textTitulo.setText(homeEntity.getTitulo());
        textDescricao.setText(homeEntity.getDescr());
        textTipo.setText(homeEntity.getTipo().getDescription());  // Ou qualquer método que converta o enum para String
        textPrec.setText(String.valueOf(homeEntity.getPreco()));  // Convertendo o preço para String
        textEndereco.setText(homeEntity.getEndereco().Forma());
        setListener(homeEntity);

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

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }


}
