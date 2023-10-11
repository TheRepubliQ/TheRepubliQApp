package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.mvp.RegisterUserMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.RegisterUserPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class RegisterUser extends AppCompatActivity implements RegisterUserMVP.View {

    private EditText edittextLogin;
    private EditText edittextCpf;
    private EditText edittextTel;
    private EditText edittextTexDtaNascimento;
    private EditText edittextEmail;
    private EditText edittextSenha;
    private Spinner spinnerGenero;
    private CheckBox checkboxProp;
    private Button btnCadastrar;
    private CheckBox visivel;
    private boolean isPasswordVisible = true;
    private RegisterUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        presenter = new RegisterUserPresenter(this, this);
        findById();
        populateGenderSpinner();
        setListeners();
    }

    private void setListeners() {
        btnCadastrar.setOnClickListener(v -> {
            String login = edittextLogin.getText().toString();
            String cpf = edittextCpf.getText().toString();
            String telefone = edittextTel.getText().toString();
            String dataNascimento = edittextTexDtaNascimento.getText().toString();
            String email = edittextEmail.getText().toString();
            String senha = edittextSenha.getText().toString();
            String genero = spinnerGenero.getSelectedItem().toString();
            boolean isOfertado = checkboxProp.isChecked();
            presenter.register(login, cpf, telefone, dataNascimento, email, senha, genero, isOfertado);
        });

        visivel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isPasswordVisible = isChecked;
            togglePasswordVisibility();
        });
    }

    private void findById() {
        edittextLogin = findViewById(R.id.edittext_Login);
        edittextCpf = findViewById(R.id.edittext_Cpf);
        edittextTel = findViewById(R.id.edittext_Tel);
        edittextTexDtaNascimento = findViewById(R.id.edittext_TexDtaNascimento);
        edittextEmail = findViewById(R.id.edittext_email);
        edittextSenha = findViewById(R.id.edittext_Senha);
        spinnerGenero = findViewById(R.id.spinner_Genero);
        checkboxProp = findViewById(R.id.checkbox_Prop);
        btnCadastrar = findViewById(R.id.btn_cad);
        visivel = findViewById(R.id.showSenha);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Cadastrar Novo Usuario");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void populateGenderSpinner() {
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Gender gender : Gender.values()) {
            genderAdapter.add(gender.getDescription());
        }

        spinnerGenero.setAdapter(genderAdapter);
    }

    private void togglePasswordVisibility() {
        int inputType = isPasswordVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        edittextSenha.setInputType(inputType);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // Fecha a atividade atual
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
