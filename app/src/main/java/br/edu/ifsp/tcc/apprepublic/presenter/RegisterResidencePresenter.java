package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.home.Tipo;
import br.edu.ifsp.tcc.apprepublic.mvp.MainActivityMVP;
import br.edu.ifsp.tcc.apprepublic.mvp.RegisterResidenceMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterResidencePresenter implements RegisterResidenceMVP.Presenter {
    private RegisterResidenceMVP.View view;
    private Context context;

    public RegisterResidencePresenter(RegisterResidenceMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }
    @Override
    public void registerResidence(HomeEntity home) {
        HomeService homeService = RESTService.getHomeService();
        Call<HomeEntity> call = homeService.createHome(getAuthorizationToken(), home);

        call.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(Call<HomeEntity> call, Response<HomeEntity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showMessage("Residencia Registrada");

                } else {
                    view.showMessage("Erro ao registrar a residência. Código de resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<HomeEntity> call, Throwable t) {
                view.showMessage("Erro de conexão ao recuperar informações da casa");
            }
        });
    }


    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String authorizationHeader = "Bearer " + accessToken;

        return authorizationHeader;
    }
}
