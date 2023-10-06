package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.tcc.apprepublic.mvp.MainActivityMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.MainActivityPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class MainActivity extends AppCompatActivity implements MainActivityMVP.View {

    private EditText textUser;
    private EditText textPassword;
    private TextView esqueciSenha;
    private Button enterButton;
    private TextView cadastrarTextView;
    private SharedPreferences sharedPreferences;
    private CheckBox lembrarDeMim;

    private CheckBox visivel;


    private MainActivityPresenter presenter;

    private boolean isPasswordVisible = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findById();
        setListener();
        presenter = new MainActivityPresenter(this, this);

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
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenha os valores dos campos de usuário e senha
                String user = textUser.getText().toString();
                String password = textPassword.getText().toString();

                // Verifique se os campos estão preenchidos
                if (user.isEmpty() || password.isEmpty()) {
                    // Exiba uma mensagem de erro indicando que ambos os campos devem ser preenchidos
                    Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Os campos estão preenchidos, prossiga com a lógica de login
                    presenter.login(user, password);

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
            }
        });

        cadastrarTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no texto "Cadastrar"
                presenter.cadast();
            }
        });

        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Adicione aqui a lógica para lidar com o clique no texto "Esqueci minha senha"
                presenter.altSenha();
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


        visivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Salvar o estado do lembrarDeMim no SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("lembrarDeMim", isLembrarDeMimChecked());
                editor.apply();
            }
        });

        visivel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Alterna a visibilidade da senha com base no estado do CheckBox
                isPasswordVisible = isChecked;
                togglePasswordVisibility();
            }
        });
    }

    private void findById() {
        textUser = findViewById(R.id.edittext_user);
        textPassword = findViewById(R.id.edittext_password);
        esqueciSenha = findViewById(R.id.esqueci_senha);
        enterButton = findViewById(R.id.button_enter);
        cadastrarTextView = findViewById(R.id.text_cadastrar);
        lembrarDeMim = findViewById(R.id.radio_remember_me);
        visivel = findViewById(R.id.showSenha);

    }

    public boolean isLembrarDeMimChecked() {
        return lembrarDeMim.isChecked();
    }

    public Context getContext() {
        return this;
    }

    public void showMessage(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private void togglePasswordVisibility() {
        // Alterna entre mostrar e ocultar a senha
        if (isPasswordVisible) {
            showPassword();
        } else {
            hidePassword();
        }
    }

    private void showPassword() {
        textPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    }

    private void hidePassword() {
        textPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

}
