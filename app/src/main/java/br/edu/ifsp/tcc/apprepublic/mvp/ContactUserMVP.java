package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.user.User;

public interface ContactUserMVP {

    interface View {
        Context getContext();

        void showMessage(String message);

        void infoContat(User user);
    }

    interface Presenter {

        void recuperarUser(long userId);

        void messageProp(User user);

    }
}
