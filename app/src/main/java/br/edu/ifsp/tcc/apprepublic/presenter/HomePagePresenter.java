package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.tcc.apprepublic.mvp.HomePageMVP;
import br.edu.ifsp.tcc.apprepublic.view.ChangeUserInformation;
import br.edu.ifsp.tcc.apprepublic.view.ListResidences;

public class HomePagePresenter implements HomePageMVP.Presenter {
    private final HomePageMVP.View view;
    private final Context context;

    public HomePagePresenter(HomePageMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void changeToRegisterResidence() {
        Intent intent = new Intent(context, ListResidences.class);
        context.startActivity(intent);
    }

    @Override
    public void changeToEditPerfil() {
        Intent intent = new Intent(context, ChangeUserInformation.class);
        context.startActivity(intent);
    }
}
