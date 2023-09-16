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

public class ChangeUserInformation extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnCad;
    private EditText edittextLogin;
    private EditText edittextCpf;
    private EditText edittextTel;
    private EditText edittextDtaNascimento;
    private EditText edittextEmail;
    private Spinner spinnerGenero;
    private CheckBox checkboxProp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        findById();
        setListener();
    }

    private void setListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no botão "Voltar"
                finish();
            }
        });

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no botão "Cadastrar"
                String login = edittextLogin.getText().toString();
                String cpf = edittextCpf.getText().toString();
                String tel = edittextTel.getText().toString();
                String dtaNascimento = edittextDtaNascimento.getText().toString();
                String email = edittextEmail.getText().toString();
                String genero = spinnerGenero.getSelectedItem().toString();
                boolean prop = checkboxProp.isChecked();

                // Implemente a lógica para processar os dados e realizar o cadastro aqui
            }
        });


        // Adicione ouvintes para outros elementos, se necessário
    }

    private void findById() {
        btnBack = findViewById(R.id.btn_back);
        btnCad = findViewById(R.id.btn_cad);
        edittextLogin = findViewById(R.id.edittext_Login);
        edittextCpf = findViewById(R.id.edittext_Cpf);
        edittextTel = findViewById(R.id.edittext_Tel);
        edittextDtaNascimento = findViewById(R.id.edittext_TexDtaNascimento);
        edittextEmail = findViewById(R.id.edittext_email);
        spinnerGenero = findViewById(R.id.spinner_Genero);
        checkboxProp = findViewById(R.id.checkbox_Prop);
    }

    public Context getContext() {
        return this;
    }

    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
