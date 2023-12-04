package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
import br.edu.ifsp.tcc.apprepublic.view.ListResidences;

public interface UserSolicitesMVP {

    interface View {
        Context getContext();
        void showMessage(String message);

        void loadDataFromApi();
    }

    interface Presenter {

        void contato(HomeEntity residence);

        void excluir(HomeEntity request);
    }
}
