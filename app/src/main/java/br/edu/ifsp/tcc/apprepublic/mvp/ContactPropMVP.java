package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;

public interface ContactPropMVP {

    interface View {
        Context getContext();

        void showMessage(String message);

        void infoContat(HomeEntity homeEntity);
    }

    interface Presenter {

        void recuperarResidences(long homeId);

        void messageProp(HomeEntity home);

    }
}
