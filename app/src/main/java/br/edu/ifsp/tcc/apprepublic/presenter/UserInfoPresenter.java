package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;

import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.PropSolicitesMVP;
import br.edu.ifsp.tcc.apprepublic.mvp.UserInfoMVP;
import br.edu.ifsp.tcc.apprepublic.view.ContactUser;
import br.edu.ifsp.tcc.apprepublic.view.UserInfo;

public class UserInfoPresenter implements UserInfoMVP.Presenter {

    private UserInfoMVP.View view;
    private Context context;

    public UserInfoPresenter(UserInfoMVP.View view, Context context) {
        this.view = view;
        this.context =context;

    }

    @Override
    public void info(User user) {
        Intent intent = new Intent(context, ContactUser.class);
        intent.putExtra("requestId", user.getId());
        context.startActivity(intent);
    }
}
