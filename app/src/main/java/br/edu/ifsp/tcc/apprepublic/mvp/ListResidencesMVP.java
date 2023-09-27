package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface ListResidencesMVP {


    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{

        void adcionarResidencia();

    }
}
