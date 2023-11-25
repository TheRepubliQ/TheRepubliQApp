package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;

public interface InfoResidencesMVP {

    interface View {
        Context getContext();
        void showMessage(String message);

        void infoHome(HomeEntity homeEntity);
    }

    interface Presenter {
        void recuperarResidences(long homeId);

    }
}
