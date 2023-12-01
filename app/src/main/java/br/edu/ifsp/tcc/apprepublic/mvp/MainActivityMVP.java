package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface MainActivityMVP {


    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{

        void login(String user, String passoword);
        void cadast();

    }
}
