package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.home.Tipo;

public interface RegisterResidenceMVP {


    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{
        void registerResidence(HomeEntity home);
    }
}
