    package br.edu.ifsp.tcc.apprepublic.presenter;

    import android.content.Context;

    import java.time.LocalDate;

    import br.edu.ifsp.tcc.apprepublic.Api.RESTService;
    import br.edu.ifsp.tcc.apprepublic.Api.TestService;
    import br.edu.ifsp.tcc.apprepublic.Api.UserService;
    import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
    import br.edu.ifsp.tcc.apprepublic.model.user.Gender;
    import br.edu.ifsp.tcc.apprepublic.model.user.User;
    import br.edu.ifsp.tcc.apprepublic.mvp.RegisterUserMVP;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class RegisterUserPresenter implements RegisterUserMVP.Presenter{
        private RegisterUserMVP.View view;
        private Context context;

        public RegisterUserPresenter(RegisterUserMVP.View view, Context context) {
            this.view = view;
            this.context = context;
        }

        @Override
        public void register(User user) {
            try {
                UserService userService = RESTService.getUserService();

                Call<User> call = userService.createUser(user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // Sucesso na chamada
                            User user = response.body();
                            if (user != null) {
                                view.showMessage("Usuário registrado com sucesso");
                            } else {
                                view.showMessage("Resposta da API não contém dados de usuário");
                            }
                        } else {
                            // Erro na chamada
                            view.showMessage("Erro na chamada da API: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        // Erro de falha na chamada
                        t.printStackTrace();
                        view.showMessage("Erro ao fazer a chamada da API: " + t.getMessage());
                    }
                });



            } catch (Exception e) {
                e.printStackTrace();
                String errorMessage = "Erro ao registrar usuário. Por favor, verifique os dados fornecidos.";
                view.showMessage(errorMessage);
            }
        }


    }
