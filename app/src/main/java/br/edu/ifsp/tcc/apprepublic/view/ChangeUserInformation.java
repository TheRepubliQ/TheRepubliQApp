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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.ChangeUserInformationMVP;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class ChangeUserInformation extends AppCompatActivity implements ChangeUserInformationMVP.View {

    private Button btnCad;
    private EditText edittextLogin;
    private EditText edittextCpf;
    private EditText edittextTel;
    private EditText edittextDtaNascimento;
    private EditText edittextEmail;
    private Spinner spinnerGenero;
    private CheckBox checkboxProp;

    private  ChangeUserInformationMVP.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        findById();
        populateGenderSpinner();
        setListener();
    }

    private void setListener() {

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long id = getUserId();
                String login = edittextLogin.getText().toString();
                String cpf = edittextCpf.getText().toString();
                String tel = edittextTel.getText().toString();
                String dtaNascimento = edittextDtaNascimento.getText().toString();
                String email = edittextEmail.getText().toString();
                String genero = spinnerGenero.getSelectedItem().toString();
                boolean prop = checkboxProp.isChecked();


                User user = new User();
                user.setId(id);
                user.setLogin(login);
                user.setCpf(cpf);
                user.setTelefone(tel);
                user.setDataNascimento(LocalDate.parse(dtaNascimento));
                user.setEmail(email);
                user.setGender(Gender.valueOf(genero));
                user.setIsProp(prop);

                presenter.changeUserInf(user);
            }


        });

    }

    private void findById() {
        btnCad = findViewById(R.id.btn_cad);
        edittextLogin = findViewById(R.id.edittext_Login);
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

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Fecha a atividade atual
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
