package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.ChangeUserInformationMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ChangeUserInformationPresenter;
import br.edu.ifsp.tcc.apprepublic.utils.DateUtils;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class ChangeUserInformation extends AppCompatActivity implements ChangeUserInformationMVP.View {

    private Button btnCad;
    private EditText edittextNome;
    private EditText edittextCpf;
    private EditText edittextDtaNascimento;

    private EditText edittextTel;

    private EditText edittextEmail;
    private Spinner spinnerGenero;
    private CheckBox checkboxProp;

    private ChangeUserInformationPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        findById();
        populateGenderSpinner();
        presenter = new ChangeUserInformationPresenter(this, this);
        populateDados();
        setListener();
    }

    private void populateDados() {

        Long id = getUserId();
        if (id != -1) {
            // Recupere as informações do usuário com base no ID
            presenter.getUserById(id);
        }
    }

    private void setListener() {
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id = getUserId();
                String nome = edittextNome.getText().toString();
                String cpf = edittextCpf.getText().toString();
                String dtaNascimento = edittextDtaNascimento.getText().toString();
                String email = edittextEmail.getText().toString();
                String tel = edittextTel.getText().toString();
                String genero = spinnerGenero.getSelectedItem().toString();
                boolean prop = checkboxProp.isChecked();

                User user = new User();
                user.setId(id);
                user.setName(nome);
                user.setCpf(cpf);
                user.setTelefone(tel);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                user.setDataNascimento(DateUtils.parseApiFormattedDate(dtaNascimento));
                user.setEmail(email);
                user.setGender(Gender.valueOf(genero.toUpperCase()));
                user.setIsProp(prop);

                presenter.changeUserInf(user);
            }
        });
    }


    private void findById() {
        btnCad = findViewById(R.id.btn_cad);
        edittextNome = findViewById(R.id.edittext_Login);
        edittextCpf = findViewById(R.id.edittext_Cpf);
        edittextTel = findViewById(R.id.edittext_Tel);
        edittextDtaNascimento = findViewById(R.id.edittext_TexDtaNascimento);
        edittextEmail = findViewById(R.id.edittext_email);
        spinnerGenero = findViewById(R.id.spinner_Genero);
        checkboxProp = findViewById(R.id.checkbox_Prop);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Alterar Seus Dados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String mensage) {
        Toast.makeText(this, mensage, Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void populateUser(User user) {

        edittextNome.setText(user.getName());
        edittextCpf.setText(user.getCpf());
        edittextTel.setText(user.getTelefone());
        LocalDate dataNascimento = user.getDataNascimento();
        if (dataNascimento != null) {
            // Antes de preencher o campo de data de nascimento, formate a data
            edittextDtaNascimento.setText(DateUtils.formatDateForApi(dataNascimento));
        } else {
            // Caso a data de nascimento seja nula, você pode tratar de alguma forma, por exemplo, exibir uma mensagem padrão.
            edittextDtaNascimento.setText("Data de Nascimento não disponível");
        }
        edittextEmail.setText(user.getEmail());
        for (int i = 0; i < spinnerGenero.getCount(); i++) {
            if (spinnerGenero.getItemAtPosition(i).toString().equals(user.getGender().getDescription())) {
                spinnerGenero.setSelection(i);
                break;
            }
        }
        checkboxProp.setChecked(user.getIsProp());


    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Cria uma nova instância da HomePage
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);

            // Fecha a atividade atual
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void populateGenderSpinner() {
        Spinner spinnerGenero = findViewById(R.id.spinner_Genero);

        // Crie um ArrayAdapter para preencher o Spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adicione os valores possíveis de gênero ao adaptador
        for (Gender gender : Gender.values()) {
            genderAdapter.add(gender.getDescription());
        }

        // Associe o adaptador ao Spinner
        spinnerGenero.setAdapter(genderAdapter);
    }

}
