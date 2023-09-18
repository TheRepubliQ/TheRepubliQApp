package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.tcc.apprepublic.model.dao.MyDatabaseHelper;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.MainActivityMVP;
import br.edu.ifsp.tcc.apprepublic.view.HomePage;
import br.edu.ifsp.tcc.apprepublic.view.RegisterUser;
import br.edu.ifsp.tcc.apprepublic.view.UpdatePassword;

public class MainActivityPresenter implements MainActivityMVP.Presenter {
    private MainActivityMVP.View view;
    private Context context;

    private MyDatabaseHelper dbHelper;

    public MainActivityPresenter(MainActivityMVP.View view, Context context) {
        this.view = view;
        this.context = context;
        dbHelper = new MyDatabaseHelper(context);

    }

    @Override
    public void login(String user, String passoword) {
         if (user.isEmpty() || passoword.isEmpty()) {
            view.showMessage("Preencha todos os campos");
        }  else {
            // Verificar o login no banco de dados SQLite
            User loggedInUser = dbHelper.validarLogin(user, passoword);
            if (loggedInUser != null) {
                Intent intent = new Intent(context, HomePage.class);
                view.showMessage("Login bem-sucedido");
                context.startActivity(intent);
                // Você pode prosseguir para a próxima atividade ou realizar outras ações aqui
            } else {
                view.showMessage("Usuário não encontrado ou senha incorreta");
            }
        }
    }




    @Override
    public void cadast() {
        Intent intent = new Intent(context, RegisterUser.class);
        view.showMessage("Registar Novo Usuario");
        context.startActivity(intent);
    }

    @Override
    public void altSenha() {
        Intent intent = new Intent(context, UpdatePassword.class);
        view.showMessage("Mude sua senha");
        context.startActivity(intent);
    }
}
