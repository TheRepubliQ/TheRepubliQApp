package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import java.util.Date;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
import br.edu.ifsp.tcc.apprepublic.model.user.User;

public interface ChangeUserInformationMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{
        void changeUserInf(User user);
    }
}
