package br.edu.ifsp.tcc.apprepublic.mvp;

import android.content.Context;

public interface MainActivityMVP {

    interface View{
        Context getContext();

        void showMessage(String erro_ao_buscar_usuário);

    }

    interface Presenter{

        void login(String user, String passoword);
        void cadast();
        void altSenha();

    }
}
