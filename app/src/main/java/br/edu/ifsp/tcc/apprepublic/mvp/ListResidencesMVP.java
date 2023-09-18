package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface ListResidencesMVP {

    interface View{
        Context getContext();

        void showMessage(String erro_ao_buscar_usuário);

    }

    interface Presenter{

        void adcionarResidencia();

    }
}
