package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.RequestService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.UserSolicitesMVP;
import br.edu.ifsp.tcc.apprepublic.view.ContactProp;
import br.edu.ifsp.tcc.apprepublic.view.EditResidence;
import br.edu.ifsp.tcc.apprepublic.view.UserSolicites;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSolicitiesPresenter implements UserSolicitesMVP.Presenter {

    private UserSolicitesMVP.View view;
    private Context context;

    public UserSolicitiesPresenter(UserSolicitesMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void contato(HomeEntity residence) {
        Intent intent = new Intent(context, ContactProp.class);
        intent.putExtra("home_id", residence.getId());
        context.startActivity(intent);
    }

    @Override
    public void excluir(HomeEntity request) {
        RequestService homeService = RESTService.getRequestService();

        Call<Void> call = homeService.deleteHomeRequest(getAuthorizationToken(), request.getId(), request.getUser().getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    view.showMessage("Casa Excluida");
                    view.loadDataFromApi();

                } else {
                    view.showMessage("Erro na exclusão");
                    view.loadDataFromApi();

                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, Throwable t) {
                view.showMessage("Erro ao realizar chamada");
            }
        });

    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String authorizationHeader = "Bearer " + accessToken;

        Log.d("Authorization Token", accessToken);
        return authorizationHeader;
    }
}
