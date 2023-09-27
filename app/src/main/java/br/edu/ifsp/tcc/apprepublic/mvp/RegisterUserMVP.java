package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface RegisterUserMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter {

        void register(String login, String cpf, String telefone, String dataNascimento, String email, String senha, String genero, boolean isOfertado);

    }
}