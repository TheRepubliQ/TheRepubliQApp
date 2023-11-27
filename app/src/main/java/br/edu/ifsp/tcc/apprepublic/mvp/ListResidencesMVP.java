package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.view.ListResidences;

public interface ListResidencesMVP {


    interface View {
        Context getContext();
        void showMessage(String message);
    }

    interface Presenter {
        void adcionarResidencia();
        void desativar(HomeEntity residence);
        void ativar(HomeEntity residence);
        void setView(View view);

        void setView(ListResidences view);

        void editar(HomeEntity residence);

        void excluir(HomeEntity residence);
    }
}
