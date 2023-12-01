package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;

public interface InfoResidencesMVP {

    interface View {
        Context getContext();
        void showMessage(String message);

        void infoHome(HomeEntity homeEntity);
    }

    interface Presenter {
        void recuperarResidences(long homeId);

        void contactProp(Request request);

    }
}
