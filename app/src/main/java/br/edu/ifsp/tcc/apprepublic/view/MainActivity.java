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
import com.jakewharton.threetenabp.AndroidThreeTen;

import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
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

    private boolean isPasswordVisible = true;

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidThreeTen.init(this);
        setContentView(R.layout.activity_main);
        findById();
        setListeners();
        presenter = new MainActivityPresenter(this, this);

        sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        boolean lembrarDeMimChecked = sharedPreferences.getBoolean("lembrarDeMim", false);
        lembrarDeMim.setChecked(lembrarDeMimChecked);
        if (lembrarDeMimChecked) {
            textUser.setText(sharedPreferences.getString("usuario", ""));
        }
    }

    private void setListeners() {
        enterButton.setOnClickListener(v -> {
            String user = textUser.getText().toString();
            String password = textPassword.getText().toString();

            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                presenter.login(user, password);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("lembrarDeMim", isLembrarDeMimChecked());
                editor.apply();

                if (isLembrarDeMimChecked()) {
                    editor.putString("usuario", user);
                } else {
                    editor.remove("usuario");
                }
                editor.apply();
            }
        });

        cadastrarTextView.setOnClickListener(v -> presenter.cadast());

        esqueciSenha.setOnClickListener(v -> presenter.altSenha());

        lembrarDeMim.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("lembrarDeMim", isChecked);
            editor.apply();
        });

        visivel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isPasswordVisible = isChecked;
            togglePasswordVisibility();
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

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void togglePasswordVisibility() {
        int inputType = isPasswordVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        textPassword.setInputType(inputType);
    }
}
