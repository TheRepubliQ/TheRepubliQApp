package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

import br.edu.ifsp.tcc.apprepublic.model.user.User;

public interface HomePageMVP {


    interface View{
        Context getContext();

        void showMessage(String mensage);

        void findById(User body);


    }

    interface Presenter{

        void changeToRegisterResidence();

        void changeToEditPerfil();

        void altSenha();

        void propSolicit();

        void userSolicit();

        void getUserById(Long id);

    }
}
