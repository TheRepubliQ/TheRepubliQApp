package br.edu.ifsp.tcc.apprepublic.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
import br.edu.ifsp.tcc.apprepublic.Api.TokenService;
import br.edu.ifsp.tcc.apprepublic.mvp.HomePageMVP;
import br.edu.ifsp.tcc.apprepublic.view.EditUser;
import br.edu.ifsp.tcc.apprepublic.view.HomePage;
import br.edu.ifsp.tcc.apprepublic.view.ListResidences;
import br.edu.ifsp.tcc.apprepublic.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePagePresenter implements HomePageMVP.Presenter {
    private final HomePageMVP.View view;
    private final Context context;

    public HomePagePresenter(HomePageMVP.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void changeToRegisterResidence() {
        Intent intent = new Intent(context, ListResidences.class);
        context.startActivity(intent);
        ((HomePage) context).finish(); // Destruir a HomePage
    }

    @Override
    public void changeToEditPerfil() {
        Intent intent = new Intent(context, EditUser.class);
        context.startActivity(intent);
        ((HomePage) context).finish(); // Destruir a HomePage
    }

    public void logout() {
        TokenService tokenService = RESTService.getTokenService();

        // Obtém o token de acesso do SharedPreferences
        String accessToken = getAuthorizationToken();

        // Certifique-se de que o token de acesso não está vazio ou nulo antes de prosseguir
        if (accessToken != null && !accessToken.isEmpty()) {
            // Cria a chamada para revogar o token, passando o token de acesso e o tipo de token no corpo da solicitação
            Call<Void> call = tokenService.revokeToken(accessToken, "access_token");

            // Adiciona o token de acesso ao cabeçalho da solicitação
            String authorizationHeader = "Bearer " + accessToken;
            call.request().newBuilder().header("Authorization", authorizationHeader);

            // Enqueue a chamada
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        view.showMessage("Deslogado com sucesso");

                        // Token revogado com sucesso, você pode implementar a lógica de logout aqui
                        // Por exemplo, limpar as preferências do usuário, navegar para a tela de login, etc.

                        // Iniciar a tela de login após o logout bem-sucedido
                        navigateToLoginScreen();
                    } else {
                        view.showMessage("Erro ao tentar deslogar - Código de resposta: " + response.code());

                        // Falha na revogação do token (verifique o código de resposta)
                        // Pode ser útil notificar o usuário sobre a falha, caso necessário
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    view.showMessage("Falha ao conectar com a API");

                    // Falha na requisição (por exemplo, problema de rede)
                    // Pode ser útil notificar o usuário sobre a falha, caso necessário
                }
            });
        } else {
            // Token de acesso não disponível
            view.showMessage("Token de acesso não disponível. Não é possível deslogar.");
        }
    }

    private void navigateToLoginScreen() {
        // Implemente a lógica para navegar para a tela de login
        // Por exemplo, criar uma Intent para a tela de login e iniciar a atividade
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

        // Certifique-se de limpar as atividades anteriores da pilha
        ((HomePage) context).finishAffinity();
    }

    private String getAuthorizationToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("Prefes", Context.MODE_PRIVATE);
        return sharedPreferences.getString("accessToken", null);
    }
}
