package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import br.edu.ifsp.tcc.apptherrepubliq.R;

public class UpdatePassword extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText edittextSenhaAtual;
    private EditText edittextNovaSenha;
    private EditText edittextConfirmarNovaSenha;
    private Button btnAlterarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

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

        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no botão "Alterar Senha"
                String senhaAtual = edittextSenhaAtual.getText().toString();
                String novaSenha = edittextNovaSenha.getText().toString();
                String confirmarNovaSenha = edittextConfirmarNovaSenha.getText().toString();

                // Implemente a lógica para alterar a senha aqui
            }
        });
    }

    private void findById() {
        btnBack = findViewById(R.id.btn_back);
        edittextSenhaAtual = findViewById(R.id.edittext_senhaAtual);
        edittextNovaSenha = findViewById(R.id.edittext_novaSenha);
        edittextConfirmarNovaSenha = findViewById(R.id.edittext_confirmarNovaSenha);
        btnAlterarSenha = findViewById(R.id.btn_alterarSenha);
    }


    public Context getContext() {
        return this;
    }

    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
