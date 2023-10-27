package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import br.edu.ifsp.tcc.apprepublic.Api.AuthService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.TestService;
import br.edu.ifsp.tcc.apprepublic.Api.UserService;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
import br.edu.ifsp.tcc.apprepublic.mvp.MainActivityMVP;
import br.edu.ifsp.tcc.apprepublic.security.TokenResponse;
import br.edu.ifsp.tcc.apprepublic.view.HomePage;
import br.edu.ifsp.tcc.apprepublic.view.RegisterUser;
import br.edu.ifsp.tcc.apprepublic.view.UpdatePassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter implements MainActivityMVP.Presenter {
    private MainActivityMVP.View view;
    private Context context;

    public MainActivityPresenter(MainActivityMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(String login, String password) {
        if (login.isEmpty() || password.isEmpty()) {
            view.showMessage("Preencha todos os campos");
        } else {
            Log.d("LoginDebug", "Login: " + login);
            Log.d("LoginDebug", "Password: " + password);

            AuthService authService = RESTService.getAuthService();

            Call<TokenResponse> call = authService.authenticate(login, password, "password");

            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(@NonNull Call<TokenResponse> call, @NonNull Response<TokenResponse> response) {
                    if (response.isSuccessful()) {
                        TokenResponse tokenResponse = response.body();
                        if (tokenResponse != null) {
                            String accessToken = tokenResponse.getAccessToken();

                            Log.d("LoginDebug", "Token de Acesso: " + accessToken);
                            getUserIDByLogin(accessToken, login);
                            view.showMessage("Login Bem Sucedido");

                            SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("accessToken", accessToken);
                            editor.apply();

                            Intent intent = new Intent(context, HomePage.class);
                            context.startActivity(intent);
                        }
                    } else {
                        view.showMessage("Usuário não encontrado ou senha incorreta");
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    t.printStackTrace();
                    view.showMessage(t.getMessage());
                }
            });
        }
    }

    public void getUserIDByLogin(String accessToken, String login) {
        if (login.isEmpty()) {
            view.showMessage("Preencha o campo de login");
        } else {
            UserService userService = RESTService.getUserService();

            Call<User> call = userService.getUserByLogin(accessToken, login);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User user = response.body();
                        if (user != null) {
                            Long user_id = user.getId();

                            // Agora você tem o ID do usuário. Você pode armazená-lo nas preferências compartilhadas.
                            SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putLong("user_id", user_id);
                            editor.apply();

                            // Continue com o login ou qualquer outra ação necessária.
                            // Neste exemplo, vou apenas mostrar uma mensagem.
                            view.showMessage("ID do usuário obtido com sucesso: " + user_id);
                        } else {
                            view.showMessage("Usuário não encontrado");
                        }
                    } else {
                        view.showMessage("Falha ao buscar o usuário");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    t.printStackTrace();
                    view.showMessage("Erro na solicitação: " + t.getMessage());
                }
            });
        }
    }

    @Override
    public void cadast() {
        Intent intent = new Intent(context, RegisterUser.class);
        view.showMessage("Registar Novo Usuario");

        context.startActivity(intent);
    }

    @Override
    public void altSenha() {
        Intent intent = new Intent(context, UpdatePassword.class);
        view.showMessage("Mude sua senha");
        context.startActivity(intent);
    }
}
