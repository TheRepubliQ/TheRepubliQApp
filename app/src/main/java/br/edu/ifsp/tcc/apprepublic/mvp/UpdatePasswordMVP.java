package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface UpdatePasswordMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{
        void alterSenha(String email, String newSenha, long userId);
    }
}
