package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.RequestService;
import br.edu.ifsp.tcc.apprepublic.Api.UserService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.InfoResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.view.ContactProp;
import br.edu.ifsp.tcc.apprepublic.view.RegisterUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoResidencePresenter implements InfoResidencesMVP.Presenter {

    private InfoResidencesMVP.View view;
    private  Context context;
    public InfoResidencePresenter(InfoResidencesMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String authorizationHeader = "Bearer " + accessToken;

        return authorizationHeader;
    }


    @Override
    public void recuperarResidences(long homeId) {
        HomeService homeService = RESTService.getHomeService();
        Call<HomeEntity> call = homeService.getHomeById(getAuthorizationToken(), homeId);

        call.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(@NonNull Call<HomeEntity> call, @NonNull Response<HomeEntity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    HomeEntity homeEntity = response.body();
                    // Aqui você pode usar os detalhes da casa recuperada
                    // Exemplo: view.showHomeDetails(homeEntity);
                    view.infoHome(homeEntity);
                } else {
                    view.showMessage("Erro ao recuperar informações da casa");
                }
            }

            @Override
            public void onFailure(@NonNull Call<HomeEntity> call, @NonNull Throwable t) {
                view.showMessage("Erro de conexão ao recuperar informações da casa");
            }
        });
    }

    @Override
    public void contactProp(Request request) {
        RequestService requestService = RESTService.getRequestService();
        Call<Request> call = requestService.createRequest(getAuthorizationToken(), request);

        call.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(@NonNull Call<Request> call, @NonNull Response<Request> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Solicitação criada com sucesso
                    view.showMessage("Solicitação enviada com sucesso!");

                } else {
                    // Exibe uma mensagem de erro genérica em caso de falha
                    view.showMessage("Erro ao enviar a solicitação. Tente novamente.");
                    Log.d("ContactProp", "Failure Response Code: " + response.code());
                    assert response.errorBody() != null;
                    Log.d("ContactProp", "Failure Response Body: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Request> call, @NonNull Throwable t) {
                // Exibe uma mensagem de erro em caso de falha na conexão
                view.showMessage("Erro de conexão ao enviar a solicitação. Verifique sua conexão com a internet.");

                Log.e("ContactProp", "Connection Error: " + t.getMessage(), t);

            }
        });
    }


}
