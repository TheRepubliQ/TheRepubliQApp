package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface HomePageMVP {


    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{

        void changeToRegisterResidence();

        void changeToEditPerfil();
    }
}
