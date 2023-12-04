package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.user.User;

public interface UserInfoMVP {
    interface View {
        Context getContext();
        void showMessage(String message);
    }

    interface Presenter {

        void info(User user);

    }
}
