package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;

import java.time.LocalDate;

import br.edu.ifsp.tcc.apprepublic.model.dao.MyDatabaseHelper;
import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.RegisterUserMVP;

public class RegisterUserPresenter implements RegisterUserMVP.Presenter{
    private RegisterUserMVP.View view;
    private Context context;

    private MyDatabaseHelper dbHelper;
    public RegisterUserPresenter(RegisterUserMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        dbHelper = new MyDatabaseHelper(context);
    }

    @Override
    public void register(String login, String cpf, String telefone, String dataNascimento, String email,
                           String senha, String genero, boolean isOfertado) {
        try {
            User newUser = new User();

            newUser.setName(login);
            newUser.setEmail(email);
            newUser.setPassword(senha);
            newUser.setTelefone(telefone);
            newUser.setDataNascimento(LocalDate.parse(dataNascimento));
            newUser.setGender(Gender.valueOf(genero));
            newUser.setLogin(login);
            newUser.setCpf(cpf);
            newUser.setIsProp(isOfertado);

            dbHelper.addUser(newUser);

            // Mensagem de sucesso
            String successMessage = "Usuário registrado com sucesso!";
            view.showMessage(successMessage);

        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Erro ao registrar usuário. Por favor, verifique os dados fornecidos.";
            view.showMessage(errorMessage);
        }
    }
}
