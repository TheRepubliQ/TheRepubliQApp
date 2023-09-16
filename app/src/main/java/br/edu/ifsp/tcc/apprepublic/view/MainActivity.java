package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.tcc.apptherrepubliq.R;

public class MainActivity extends AppCompatActivity {

    private EditText textUser;
    private EditText textPassword;
    private TextView esqueciSenha;
    private Button enterButton;
    private TextView cadastrarTextView;
    private SharedPreferences sharedPreferences;
    private CheckBox lembrarDeMim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        setListener();


        //CRIAÇÃO DO SHARED PREFERENCES PARA GUARDAR USERNAME DO USUARIO
        sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        boolean lembrarDeMimChecked = sharedPreferences.getBoolean("lembrarDeMim", false);
        lembrarDeMim.setChecked(lembrarDeMimChecked);
        if (lembrarDeMimChecked) {
            String savedUser = sharedPreferences.getString("usuario", "");
            textUser.setText(savedUser);
        }
    }

    private void setListener() {
        textUser = findViewById(R.id.edittext_user);
        textPassword = findViewById(R.id.edittext_password);
        esqueciSenha = findViewById(R.id.esqueci_senha);
        enterButton = findViewById(R.id.button_enter);
        cadastrarTextView = findViewById(R.id.text_cadastrar);
        lembrarDeMim = findViewById(R.id.radio_remember_me);
    }

    private void findById() {
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no botão "Entrar"
                String user = textUser.getText().toString();
                String password = textPassword.getText().toString();

                // Salvar o estado do lembrarDeMim no SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("lembrarDeMim", isLembrarDeMimChecked());
                editor.apply();

                if (isLembrarDeMimChecked()) {
                    // Salvar o usuário no SharedPreferences
                    editor.putString("usuario", user);
                    editor.apply();
                } else {
                    // Remover o usuário do SharedPreferences
                    editor.remove("usuario");
                    editor.apply();
                }
            }
        });

        cadastrarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no texto "Cadastrar"

            }
        });

        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no texto "Esqueci minha senha"
            }
        });

        lembrarDeMim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Salvar o estado do lembrarDeMim no SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("lembrarDeMim", isLembrarDeMimChecked());
                editor.apply();
            }
        });
    }

    public boolean isLembrarDeMimChecked() {
        return lembrarDeMim.isChecked();
    }

    public Context getContext() {
        return this;
    }

    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}
