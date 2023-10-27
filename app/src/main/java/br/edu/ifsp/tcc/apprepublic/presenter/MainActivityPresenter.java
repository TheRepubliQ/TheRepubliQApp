package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import br.edu.ifsp.tcc.apprepublic.Api.AuthService;
import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.TestService;
import br.edu.ifsp.tcc.apprepublic.mvp.MainActivityMVP;
import br.edu.ifsp.tcc.apprepublic.security.TokenResponse;
import br.edu.ifsp.tcc.apprepublic.view.HomePage;
import br.edu.ifsp.tcc.apprepublic.view.RegisterUser;
import br.edu.ifsp.tcc.apprepublic.view.UpdatePassword;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;

public class MainActivityPresenter implements MainActivityMVP.Presenter {
    private MainActivityMVP.View view;
    private Context context;


    public MainActivityPresenter(MainActivityMVP.View view, Context context) {
        this.view = view;
        this.context = context;

    }

    @Override
    public void login(String login, String password) {
        //testConect();

        if (login.isEmpty() || password.isEmpty()) {
            view.showMessage("Preencha todos os campos");
        } else {
            // Exibir os valores de login e password antes de fazer a solicitação
            Log.d("LoginDebug", "Login: " + login);
            Log.d("LoginDebug", "Password: " + password);

            // Use o AuthService para fazer a solicitação de autenticação
            AuthService authService = RESTService.getAuthService();

            // Chame a função de autenticação passando as credenciais do usuário
            Call<TokenResponse> call = authService.authenticate(login, password, "password");

            call.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(@NonNull Call<TokenResponse> call, @NonNull Response<TokenResponse> response) {
                    if (response.isSuccessful()) {
                        TokenResponse tokenResponse = response.body();
                        if (tokenResponse != null) {
                            String accessToken = tokenResponse.getAccessToken();
                            // Log para exibir o token de acesso
                            Log.d("LoginDebug", "Token de Acesso: " + accessToken);
                        }
                        // Resto do código para redirecionar para a próxima atividade e mostrar uma mensagem de login bem-sucedido
                    } else {
                        // Trate o caso de login sem sucesso (usuário não encontrado ou senha incorreta)
                        view.showMessage("Usuário não encontrado ou senha incorreta");
                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    t.printStackTrace();
                    // Trate erros de rede ou outros erros aqui
                }
            });
        }
    }


    public void testConect() {
        // Use o TestService para fazer a solicitação de teste de conexão
        TestService testService = RESTService.getTestService();

        Call<String> testCall = testService.testEndpoint();

        testCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    // Conexão com o servidor de teste bem-sucedida
                    // Você pode adicionar aqui qualquer tratamento ou ações necessárias
                    // após a conexão com o servidor de teste.
                    // Por exemplo, exibir uma mensagem ou executar outra ação.
                    view.showMessage("Conexão com o servidor de teste bem-sucedida");
                } else {
                    // Trate o caso de falha na conexão
                    // Por exemplo, exibir uma mensagem de erro.
                    view.showMessage("Falha na conexão com o servidor de teste");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                // Trate erros de rede ou outros erros aqui
                // Por exemplo, exibir uma mensagem de erro ou fazer outra ação.
                view.showMessage(t.getMessage());
                System.out.println("Erro foi: " + t.getMessage());
            }
        });
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
