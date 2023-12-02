package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.mvp.ContactPropMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPropPresenter implements ContactPropMVP.Presenter {

    private ContactPropMVP.View view;
    private Context context;

    public ContactPropPresenter(ContactPropMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void recuperarResidences(long homeId) {
        Log.d("ContactPropPresenter", "Recuperando informações para o homeId: " + homeId);

        HomeService homeService = RESTService.getHomeService();
        Call<HomeEntity> call = homeService.getHomeById(getAuthorizationToken(), homeId);

        call.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(Call<HomeEntity> call, Response<HomeEntity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    HomeEntity homeEntity = response.body();
                    // Aqui você pode usar os detalhes da casa recuperada
                    // Exemplo: view.showHomeDetails(homeEntity);
                    view.infoContat(homeEntity);
                } else {
                    view.showMessage("Erro ao recuperar informações da casa");
                    Log.d("ContactPropPresenter", "Falha na resposta do servidor: " + response.code());
                    // Adicione mais informações relevantes aos logs conforme necessário
                }
            }

            @Override
            public void onFailure(Call<HomeEntity> call, Throwable t) {
                view.showMessage("Erro de conexão ao recuperar informações da casa");
                Log.e("ContactPropPresenter", "Erro durante a solicitação ao servidor", t);
            }
        });
    }

    @Override
    public void messageProp() {
        // Implemente a lógica para exibir a mensagem do proprietário aqui, se necessário.
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        String accessToken = sharedPreferences.getString("accessToken", null);
        String authorizationHeader = "Bearer " + accessToken;

        return authorizationHeader;
    }
}
