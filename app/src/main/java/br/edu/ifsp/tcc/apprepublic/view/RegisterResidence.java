package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.Collator;
import java.util.Locale;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.home.Address;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.home.Tipo;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.RegisterResidenceMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.RegisterResidencePresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class RegisterResidence extends AppCompatActivity implements RegisterResidenceMVP.View {

    private RegisterResidencePresenter presenter;
    private EditText edittexDesc;
    private EditText edittextTitulo;
    private EditText edittextPrec;
    private EditText edittextCep;
    private EditText edittextNum;
    private EditText edittextPais;
    private EditText edittextEstado;
    private EditText edittextCidade;
    private EditText edittextBairro;
    private EditText edittextRua;
    private EditText edittextComplemento;
    private CheckBox ofertado;
    private Spinner tipoMoradia;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_residence);

        findById();
        presenter = new RegisterResidencePresenter(this, this);
        presenter.getUserById(getUserId());
        populateTipoMoradiaSpinner();
    }

    private void setListener(User body) {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para lidar com o clique no botão "Cadastrar"
                String titulo = edittextTitulo.getText().toString();
                String desc = edittexDesc.getText().toString();
                String prec = edittextPrec.getText().toString();
                String cep = edittextCep.getText().toString();
                String pais = edittextPais.getText().toString();
                String estado = edittextEstado.getText().toString();
                String cidade = edittextCidade.getText().toString();
                String bairro = edittextBairro.getText().toString();
                String rua = edittextRua.getText().toString();
                String num = edittextNum.getText().toString();
                Collator collator = Collator.getInstance(new Locale("pt", "BR"));
                collator.setStrength(Collator.NO_DECOMPOSITION); // Ignorar acentos
                String complemento = edittextComplemento.getText().toString().toUpperCase(Locale.ROOT);
                boolean isOfertado = ofertado.isChecked();
                String moradia = tipoMoradia.getSelectedItem().toString();

                if (titulo.isEmpty() || desc.isEmpty() || prec.isEmpty() || cep.isEmpty() || num.isEmpty()
                        || pais.isEmpty()|| estado.isEmpty()|| cidade.isEmpty()|| bairro.isEmpty()|| rua.isEmpty()) {
                    showMessage("Preencha todos os campos!");
                } else {
                    HomeEntity home = new HomeEntity();
                    Address endereco = new Address();

                    endereco.setCep(cep);
                    endereco.setPais(pais);
                    endereco.setEstado(estado);
                    endereco.setCidade(cidade);
                    endereco.setBairro(bairro);
                    endereco.setRua(rua);
                    endereco.setNumero(num);
                    endereco.setComplemento(complemento);
                    home.setTitulo(titulo);
                    home.setDescr(desc);
                    home.setPreco(Float.parseFloat(prec));
                    home.setOfertado(isOfertado);

                    // Converta a string para minúsculas e remova acentos
                    String moradiaLowerCase = removeAcentos(moradia.toLowerCase(Locale.ROOT));

                    // Compare com os valores do enum Tipo
                    Tipo tipoSelecionado = null;
                    for (Tipo tipo : Tipo.values()) {
                        String tipoEnumLowerCase = removeAcentos(tipo.getDescription().toLowerCase(Locale.ROOT));
                        if (tipoEnumLowerCase.equals(moradiaLowerCase)) {
                            tipoSelecionado = tipo;
                            break;
                        }
                    }

                    // Se tipoSelecionado ainda for nulo, significa que a string não corresponde a nenhum valor do enum
                    if (tipoSelecionado == null) {
                        showMessage("Tipo de moradia inválido");
                    } else {
                        home.setTipo(tipoSelecionado);
                        home.setEndereco(endereco);
                        home.setUser(body);

                        presenter.registerResidence(home);
                    }
                }
            }
        });
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Resgistrar nova residência");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // Encontre os elementos da interface por ID
        edittextTitulo= findViewById(R.id.edittext_Titulo);
        edittexDesc = findViewById(R.id.edittext_DescricaoMoradia);
        edittextPrec = findViewById(R.id.edittext_PrecoMoradia);
        edittextCep = findViewById(R.id.EditText_textCEP);
        edittextNum = findViewById(R.id.edittext_textNumero);
        edittextPais = findViewById(R.id.edittext_Pais);
        edittextEstado = findViewById(R.id.edittext_Estado);
        edittextCidade = findViewById(R.id.edittext_Cidade);
        edittextBairro = findViewById(R.id.edittext_Bairro);
        edittextRua = findViewById(R.id.edittext_Rua);
        edittextComplemento = findViewById(R.id.edittext_Complemento);
        ofertado = findViewById(R.id.checkbox_OfertadoMoradia);
        tipoMoradia = findViewById(R.id.spinner_TipoMoradia);
        cadastrar = findViewById(R.id.btn_cadMoradia);


    }

    // Função para remover acentos de uma string
    private String removeAcentos(String str) {
        return str.replaceAll("[^\\p{ASCII}]", "");
    }
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public User populateUser(User body) {
        setListener(body);
        return body;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Fecha a atividade atual
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateTipoMoradiaSpinner() {
        Spinner spinnerTipoMoradia = findViewById(R.id.spinner_TipoMoradia);

        // Crie um ArrayAdapter para preencher o Spinner
        ArrayAdapter<String> tipoMoradiaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        tipoMoradiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adicione os valores do enum Tipo ao adaptador
        for (Tipo tipo : Tipo.values()) {
            tipoMoradiaAdapter.add(tipo.getDescription());
        }

        // Associe o adaptador ao Spinner
        spinnerTipoMoradia.setAdapter(tipoMoradiaAdapter);
    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }

}
