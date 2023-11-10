package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.UserService;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.ChangeUserInformationMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeUserInformationPresenter implements ChangeUserInformationMVP.Presenter {

    private final ChangeUserInformationMVP.View view;
    private final Context context;

    public ChangeUserInformationPresenter(ChangeUserInformationMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void changeUserInf(User user) {
        UserService userService = RESTService.getUserService();

        Call<User> call = userService.updateUser(getAuthorizationToken(), user.getId(), user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    // Informe à View que os dados foram alterados com sucesso
                    view.showMessage("Dados alterados");
                } else {
                    // Informe à View sobre falha na atualização
                    view.showMessage("Falha ao alterar os dados");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                // Informe à View sobre falha na chamada da API
                view.showMessage("Erro na solicitação da API: " + t.getMessage());
            }
        });
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getString("accessToken", null);
    }
}

