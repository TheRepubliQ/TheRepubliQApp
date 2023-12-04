package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.PropSolicitesMVP;
import br.edu.ifsp.tcc.apprepublic.view.UserInfo;

public class PropSolicitiesPresenter implements PropSolicitesMVP.Presenter {

    private PropSolicitesMVP.View view;
    private Context context;
    public PropSolicitiesPresenter(PropSolicitesMVP.View view, Context context) {
        this.view = view;
        this.context =context;

    }

    @Override
    public void info(HomeEntity request) {
        Intent intent = new Intent(context, UserInfo.class);
        intent.putExtra("requestId", request.getId());
        context.startActivity(intent);
    }

}
