package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.mvp.UpdatePasswordMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.UpdatePasswordPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class UpdatePassword extends AppCompatActivity implements UpdatePasswordMVP.View {

    private EditText edittextEmail;
    private EditText edittextNovaSenha;
    private EditText edittextConfirmarNovaSenha;
    private Button btnAlterarSenha;
    private CheckBox visivel;
    private CheckBox visivelCon;

    private boolean isPasswordVisible = true;
    private boolean isPasswordVisibleCon = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        findById();
        setListeners();
    }

    private void setListeners() {
        btnAlterarSenha.setOnClickListener(v -> {
            UpdatePasswordPresenter presenter = new UpdatePasswordPresenter(this, this);
            String Email = edittextEmail.getText().toString();
            String novaSenha = edittextNovaSenha.getText().toString();
            String confirmarNovaSenha = edittextConfirmarNovaSenha.getText().toString();
            if(novaSenha.equals(confirmarNovaSenha)){
                presenter.alterSenha(Email, novaSenha, getUserId());

            }
         });

        visivel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isPasswordVisible = isChecked;
            togglePasswordVisibility(isPasswordVisible, edittextNovaSenha);
        });

        visivelCon.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isPasswordVisibleCon = isChecked;
            togglePasswordVisibility(isPasswordVisibleCon, edittextConfirmarNovaSenha);
        });
    }

    private void findById() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Alterar Senha");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edittextEmail = findViewById(R.id.edittext_email);
        edittextNovaSenha = findViewById(R.id.edittext_novaSenha);
        edittextConfirmarNovaSenha = findViewById(R.id.edittext_confirmarNovaSenha);
        btnAlterarSenha = findViewById(R.id.btn_alterarSenha);
        visivel = findViewById(R.id.showSenha);
        visivelCon = findViewById(R.id.showConSenha);
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void togglePasswordVisibility(boolean isVisible, EditText editText) {
        // Alterna entre mostrar e ocultar a senha
        int inputType = isVisible ?
                InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD :
                (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        editText.setInputType(inputType);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Use o TaskStackBuilder para criar uma pilha de navegação
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(new Intent(this, HomePage.class))
                    .startActivities();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }
}
