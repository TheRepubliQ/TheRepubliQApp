package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import java.util.Date;

import br.edu.ifsp.tcc.apprepublic.model.user.Gender;

public interface ChangeUserInformationMVP {

    interface View{
        Context getContext();

        void showMessage(String mensage);

    }

    interface Presenter{
        void changeUserInf(String Login, String Cpf, String tel, Date daaNasci, String email, String senha, Gender genero, Boolean ofertado);
    }
}
