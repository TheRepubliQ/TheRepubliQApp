package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.user.User;

public interface EditResidenceMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);

        User populateUser(User body);

        void handleHome(HomeEntity body);
    }

    interface Presenter{
        void editResidence(HomeEntity home, Long id);

        void getResidenceById(Long id);
    }
}
