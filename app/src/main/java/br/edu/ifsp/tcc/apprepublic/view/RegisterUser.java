package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.mvp.RegisterUserMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.MainActivityPresenter;
import br.edu.ifsp.tcc.apprepublic.presenter.RegisterUserPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class RegisterUser extends AppCompatActivity implements RegisterUserMVP.View {

    private ImageButton btnBack;
    private EditText edittextLogin;
    private EditText edittextCpf;
    private EditText edittextTel;
    private EditText edittextTexDtaNascimento;
    private EditText edittextEmail;
    private EditText edittextSenha;
    private Spinner spinnerGenero;
    private CheckBox checkboxProp;
    private Button btnCadastrar;

    private RegisterUserPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        presenter = new RegisterUserPresenter(this, this);
    

        findById();
        populateGenderSpinner(); // Chame o método para preencher o Spinner
        setListener();

    }

    private void setListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para lidar com o clique no botão de voltar
                finish(); // Feche a atividade atual
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para lidar com o clique no botão de cadastrar
                // Você pode acessar os valores dos campos de entrada aqui, por exemplo:
                String login = edittextLogin.getText().toString();
                String cpf = edittextCpf.getText().toString();
                String telefone = edittextTel.getText().toString();
                String dataNascimento = edittextTexDtaNascimento.getText().toString();
                String email = edittextEmail.getText().toString();
                String senha = edittextSenha.getText().toString();
                String genero = spinnerGenero.getSelectedItem().toString();
                boolean isOfertado = checkboxProp.isChecked();

                presenter.register(login, cpf, telefone, dataNascimento, email, senha, genero, isOfertado);

            }
        });
    }


    private void findById() {
        btnBack = findViewById(R.id.btn_back);
        edittextLogin = findViewById(R.id.edittext_Login);
        edittextCpf = findViewById(R.id.edittext_Cpf);
        edittextTel = findViewById(R.id.edittext_Tel);
        edittextTexDtaNascimento = findViewById(R.id.edittext_TexDtaNascimento);
        edittextEmail = findViewById(R.id.edittext_email);
        edittextSenha = findViewById(R.id.edittext_Senha);
        spinnerGenero = findViewById(R.id.spinner_Genero);
        checkboxProp = findViewById(R.id.checkbox_Prop);
        btnCadastrar = findViewById(R.id.btn_cad);
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String mensage) {
        Toast.makeText(this, mensage, Toast.LENGTH_SHORT).show();


    }

    private void populateGenderSpinner() {
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Adicione os valores possíveis de gênero ao adaptador
        for (Gender gender : Gender.values()) {
            genderAdapter.add(gender.getDescription());
        }

        spinnerGenero.setAdapter(genderAdapter);
    }

}