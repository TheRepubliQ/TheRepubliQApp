package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.tcc.apptherrepubliq.R;

public class RegisterUser extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        findById();
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

                // Realize as ações necessárias com os dados acima, como validação e envio para o servidor.
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

    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}