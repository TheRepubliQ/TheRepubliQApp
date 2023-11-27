package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.user.User;

public interface RegisterUserMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter {

        void register(User user);

    }
}