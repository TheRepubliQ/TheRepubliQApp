package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.HomeService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.UserService;
import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.EditResidenceMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditResidencePresenter implements EditResidenceMVP.Presenter {
    private EditResidenceMVP.View view;
    private Context context;

    public EditResidencePresenter(EditResidenceMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void editResidence(HomeEntity home, Long id) {

        HomeService homeService = RESTService.getHomeService();
        Call<HomeEntity> call = homeService.updateHome(getAuthorizationToken(), id, home);

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

    public void getUserById(Long id) {
        UserService userService = RESTService.getUserService();

        Call<User> call = userService.getUserById(getAuthorizationToken(), id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                int responseCode = response.code();
                if (response.isSuccessful()) {
                    // Informe à View para preencher os dados do usuário
                    view.populateUser(response.body());
                } else {
                    // Informe à View sobre falha na obtenção dos dados do usuário
                    view.showMessage("Falha ao obter os dados do usuário. Código de resposta: " + responseCode);
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                // Informe à View sobre falha na chamada da API
                view.showMessage("Erro na solicitação da API: " + t.getMessage());
                Log.d("Erro filha da puta",  t.getMessage());

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
