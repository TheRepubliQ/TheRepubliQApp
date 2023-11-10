package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.UserService;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.UpdatePasswordMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasswordPresenter implements UpdatePasswordMVP.Presenter {

    private UpdatePasswordMVP.View view;
    private Context context;

    public UpdatePasswordPresenter(UpdatePasswordMVP.View view, Context context, UserService userService) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void alterSenha(String email, String newSenha) {
        UserService userService = RESTService.getUserService();

        userService.getUserByEmail("Bearer " + getAccessToken(), email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    assert user != null;
                    user.setPassword(newSenha);

                    // Atualizar o usuário com a nova senha
                    userService.updateUser(getAccessToken(), user.getId(), user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                            if (response.isSuccessful()) {
                                // Senha alterada com sucesso
                                view.showMessage("Senha alterada com sucesso");
                            } else {
                                view.showMessage("Falha ao alterar a senha");
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                            view.showMessage("Erro na solicitação da API: " + t.getMessage());
                        }
                    });
                } else {
                    view.showMessage("Falha ao obter o usuário pelo e-mail");
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                view.showMessage("Erro na solicitação da API: " + t.getMessage());
            }
        });
    }

    // Método fictício para obter o token de acesso (substitua pelo método real)
    private String getAccessToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getString("accessToken", null);
    }
}
