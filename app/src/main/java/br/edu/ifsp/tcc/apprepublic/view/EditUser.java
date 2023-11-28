package br.edu.ifsp.tcc.apprepublic.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.ChangeUserInformationMVP;
import br.edu.ifsp.tcc.apprepublic.presenter.ChangeUserInformationPresenter;
import br.edu.ifsp.tcc.apptherrepubliq.R;

public class EditUser extends AppCompatActivity implements ChangeUserInformationMVP.View {

    private Button btnCad;
    private EditText edittextNome;
    private EditText edittextCpf;
    private EditText edittextDtaNascimento;

    private EditText edittextTel;

    private EditText edittextEmail;
    private Spinner spinnerGenero;
    private CheckBox checkboxProp;

    private ChangeUserInformationPresenter presenter;
    private User user; // Adicione um campo para armazenar o objeto User

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_information);

        findById();
        populateGenderSpinner();
        presenter = new ChangeUserInformationPresenter(this, this);
        presenter.getUser(getUserId());
        populateDados();
    }

    private void populateDados() {
        Long id = getUserId();
        if (id != -1) {
            // Recupere as informações do usuário com base no ID
            presenter.getUserById(id);
        }
    }

    private void setListener() {
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifique se o objeto User não é nulo antes de fazer alterações


                if (user != null) {
                    String nome = edittextNome.getText().toString();
                    String cpf = edittextCpf.getText().toString();
                    String dtaNascimento = edittextDtaNascimento.getText().toString();
                    String email = edittextEmail.getText().toString();
                    String tel = edittextTel.getText().toString();
                    String genero = spinnerGenero.getSelectedItem().toString();
                    boolean prop = checkboxProp.isChecked();

                    user.setName(nome);
                    user.setCpf(cpf);
                    user.setTelefone(tel);
                    user.setDataNascimento(dtaNascimento);
                    user.setEmail(email);
                    user.setGender(Gender.valueOf(genero.toUpperCase()));
                    user.setIsProp(prop);

                    presenter.changeUserInf(user);
                } else {
                    // Lidar com a situação em que o objeto User é nulo
                    showMessage("Erro: Usuário não encontrado");
                }
            }
        });
    }

    private void findById() {
        btnCad = findViewById(R.id.btn_cad);
        edittextNome = findViewById(R.id.edittext_Login);
        edittextCpf = findViewById(R.id.edittext_Cpf);
        edittextTel = findViewById(R.id.edittext_Tel);
        edittextDtaNascimento = findViewById(R.id.edittext_TexDtaNascimento);
        edittextEmail = findViewById(R.id.edittext_email);
        spinnerGenero = findViewById(R.id.spinner_Genero);
        checkboxProp = findViewById(R.id.checkbox_Prop);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Alterar Seus Dados");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleUser(User user) {
        this.user = user;
        populateUser(user);
        setListener(); // Chame o método setListener após obter o objeto User
    }

    private long getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", -1); // Retorne -1 se o ID não estiver disponível
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateGenderSpinner() {
        Spinner spinnerGenero = findViewById(R.id.spinner_Genero);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Gender gender : Gender.values()) {
            genderAdapter.add(gender.getDescription());
        }

        spinnerGenero.setAdapter(genderAdapter);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void populateUser(User user) {
        edittextNome.setText(user.getName());
        edittextCpf.setText(user.getCpf());
        edittextTel.setText(user.getTelefone());
        edittextDtaNascimento.setText((user.getDataNascimento()));
        edittextEmail.setText(user.getEmail());

        for (int i = 0; i < spinnerGenero.getCount(); i++) {
            if (spinnerGenero.getItemAtPosition(i).toString().equals(user.getGender().getDescription())) {
                spinnerGenero.setSelection(i);
                break;
            }
        }
        checkboxProp.setChecked(user.getIsProp());
    }
}
