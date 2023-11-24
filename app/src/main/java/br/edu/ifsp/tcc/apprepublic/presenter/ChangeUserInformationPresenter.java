    package br.edu.ifsp.tcc.apprepublic.presenter;

    import android.content.Context;
    import android.content.SharedPreferences;
    import android.os.AsyncTask;
    import android.util.Log;

    import androidx.annotation.NonNull;

    import java.io.IOException;
    import java.time.format.DateTimeFormatter;

    import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
    import br.edu.ifsp.tcc.apprepublic.Api.UserService;
    import br.edu.ifsp.tcc.apprepublic.model.user.User;
    import br.edu.ifsp.tcc.apprepublic.mvp.ChangeUserInformationMVP;
    import br.edu.ifsp.tcc.apprepublic.utils.DateUtils;
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
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Call<User> call = userService.updateUser(getAuthorizationToken(), user.getId(), user);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    int responseCode = response.code();
                    if (response.isSuccessful()) {
                        // Informe à View que os dados foram alterados com sucesso
                        view.showMessage("Dados alterados");
                    } else {
                        // Informe à View sobre falha na atualização
                        try {
                            // Tente obter o corpo da resposta de erro
                            String errorBody = response.errorBody().string();
                            view.showMessage("Falha ao alterar os dados. Código de resposta: " + responseCode +
                                    "\nDetalhes do erro: " + errorBody);
                        } catch (IOException e) {
                            e.printStackTrace();
                            view.showMessage("Erro ao processar resposta de erro");
                        }
                    }
                }


                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    // Informe à View sobre falha na chamada da API
                    view.showMessage("Erro na solicitação da API: " + t.getMessage());
                }
            });
        }

        @Override
        public User getUser(Long id) {
            UserService userService = RESTService.getUserService();

            Call<User> call = userService.getUserById(getAuthorizationToken(), id);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    int responseCode = response.code();
                    if (response.isSuccessful()) {
                        // Retorna o usuário obtido com sucesso
                        User user = response.body();
                        // Faça algo com o usuário (por exemplo, chame um método na View)
                        view.handleUser(user);
                    } else {
                        // Se a chamada não for bem-sucedida, trate o erro ou retorne null (ou um valor padrão)
                        view.showMessage("Falha ao obter os dados do usuário. Código de resposta: " + responseCode);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                    // Trate falhas na chamada da API
                    view.showMessage("Erro na solicitação da API: " + t.getMessage());
                    Log.e("Erro na chamada da API", t.getMessage(), t);
                }
            });

            // O valor de retorno aqui não será imediatamente disponível, pois a chamada é assíncrona
            return null;
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


        private class GetUserTask extends AsyncTask<Long, Void, User> {

            @Override
            protected User doInBackground(Long... params) {
                Long id = params[0];
                UserService userService = RESTService.getUserService();

                Call<User> call = userService.getUserById(getAuthorizationToken(), id);

                try {
                    // Execute a chamada de forma síncrona
                    Response<User> response = call.execute();

                    int responseCode = response.code();
                    if (response.isSuccessful()) {
                        // Retorna o usuário obtido com sucesso
                        return response.body();
                    } else {
                        // Se a chamada não for bem-sucedida, trate o erro ou retorne null (ou um valor padrão)
                        // Aqui, você pode lançar uma exceção ou retornar um valor padrão
                    }
                } catch (IOException e) {
                    // Trate exceções de IO, por exemplo, se houver um problema de conexão
                    // Aqui, você pode lançar uma exceção ou retornar um valor padrão
                }

                // Se ocorrer algum erro, retorne null (ou um valor padrão)
                return null;
            }

            @Override
            protected void onPostExecute(User user) {
                // Este método é chamado na thread principal após a conclusão da tarefa em segundo plano
                // Aqui, você pode notificar a view ou realizar outras ações com o usuário retornado
                if (user != null) {
                    // Notifique a view ou realize outras ações
                } else {
                    // Trate a situação em que o usuário é nulo (erro na chamada API)
                }
            }
        }
    }

