package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.home.Tipo;
import br.edu.ifsp.tcc.apprepublic.mvp.RegisterResidenceMVP;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class RegisterResidence extends AppCompatActivity implements RegisterResidenceMVP.View {

    private EditText edittexDesc;
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
        setListener();
        populateTipoMoradiaSpinner();
    }

    private void setListener() {
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para lidar com o clique no botão "Cadastrar"
                String desc = edittexDesc.getText().toString();
                String prec = edittextPrec.getText().toString();
                String cep = edittextCep.getText().toString();
                String num = edittextNum.getText().toString();
                String pais = edittextPais.getText().toString();
                String estado = edittextEstado.getText().toString();
                String cidade = edittextCidade.getText().toString();
                String bairro = edittextBairro.getText().toString();
                String rua = edittextRua.getText().toString();
                String complemento = edittextComplemento.getText().toString();
                boolean isOfertado = ofertado.isChecked();
                String moradia = tipoMoradia.getSelectedItem().toString();

                // Realize a validação dos dados, por exemplo:
                if (desc.isEmpty() || prec.isEmpty() || cep.isEmpty() || num.isEmpty()
                || pais.isEmpty()|| estado.isEmpty()|| cidade.isEmpty()|| bairro.isEmpty()|| rua.isEmpty()) {
                    showMessage("Preencha todos os campos!");
                } else {
                    // Crie uma instância do objeto de residência e faça o que for necessário
                    // Residence residence = new Residence(desc, prec, cep, num, isOfertado, moradia);

                    // Exemplo: Salvar no banco de dados ou realizar outra ação
                    // residenceRepository.save(residence);

                    // Exiba uma mensagem de sucesso
                    showMessage("Residência cadastrada com sucesso!");
                }
            }
        });
    }

    private void findById() {
        // Encontre os elementos da interface por ID
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

        Objects.requireNonNull(getSupportActionBar()).setTitle("Resgistrar nova residência");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
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
}
