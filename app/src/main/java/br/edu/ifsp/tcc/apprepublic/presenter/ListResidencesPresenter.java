package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.ListResidencesMVP;
import br.edu.ifsp.tcc.apprepublic.view.ListResidences;
import br.edu.ifsp.tcc.apprepublic.view.RegisterResidence;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListResidencesPresenter implements ListResidencesMVP.Presenter {
    private ListResidences view;
    private Context context;




    public ListResidencesPresenter(ListResidences view, Context context) {
        this.view = view;
        this.context = context;

    }

    @Override
    public void adcionarResidencia() {
        Intent intent = new Intent(context, RegisterResidence.class);
        context.startActivity(intent);
    }

    @Override
    public void desativar(HomeEntity residence) {

        HomeService homeService = RESTService.getHomeService();

        Call<HomeEntity> call = homeService.desactivityHome(getAuthorizationToken(), residence.getId());
        call.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(@NonNull Call<HomeEntity> call, @NonNull Response<HomeEntity> response) {
                if (response.isSuccessful()) {
                    view.showMessage("Não está mais ofertada");
                    view.loadDataFromApi();

                } else {
                    view.showMessage("Não está mais ofertada");
                    view.loadDataFromApi();
                }
            }

            @Override
            public void onFailure(Call<HomeEntity> call, Throwable t) {
                view.showMessage("Erro ao realizar chamada");
            }
        });
    }

    @Override
    public void ativar(HomeEntity residence) {

        HomeService homeService = RESTService.getHomeService();

        Call<HomeEntity> call = homeService.activityHome(getAuthorizationToken(), residence.getId());
        call.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(@NonNull Call<HomeEntity> call, @NonNull Response<HomeEntity> response) {
                if (response.isSuccessful()) {
                    view.showMessage("Casa ofertada");
                    view.loadDataFromApi();

                } else {
                 //   view.showMessage("Erro ao colocar moradia em oferta");
                    view.showMessage("Casa ofertada");
                    view.loadDataFromApi();

                }
            }

            @Override
            public void onFailure(@NonNull Call<HomeEntity> call, Throwable t) {
                view.showMessage("Erro ao realizar chamada");
            }
        });
    }

    @Override
    public void setView(ListResidencesMVP.View view) {
        this.view = (ListResidences) view;
    }

    @Override
    public void setView(ListResidences view) {
        this.view = view;
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String authorizationHeader = "Bearer " + accessToken;

        Log.d("Authorization Token", accessToken);
        return authorizationHeader;
    }

}
