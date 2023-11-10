package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;

public interface ListResidencesMVP {


    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{

        void adcionarResidencia();

        void desativar(HomeEntity residence);

        void ativar(HomeEntity residence);
    }
}
