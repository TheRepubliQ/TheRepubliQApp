    package br.edu.ifsp.tcc.apprepublic.Api;

    import java.util.List;

    import br.edu.ifsp.tcc.apprepublic.model.user.User;
    import retrofit2.Call;
    import retrofit2.http.Body;
    import retrofit2.http.DELETE;
    import retrofit2.http.GET;
    import retrofit2.http.Header;
    import retrofit2.http.POST;
    import retrofit2.http.PUT;
    import retrofit2.http.Path;

    public interface UserService {

        @POST("user") // Endpoint para criar um novo usuário
        Call<User> createUser(@Body User user);

        @GET("user") // Endpoint para listar todos os usuários
        Call<List<User>> listUsers();

        @GET("user/{id}") // Endpoint para buscar um usuário pelo ID
        Call<User> getUserById(@Header("Authorization") String authorization, @Path("id") Long id);

        @GET("user/email/{email}") // Endpoint para buscar um usuário pelo ID
        Call<User> getUserByEmail(@Header("Authorization") String authorization, @Path("email") String email);

        @PUT("user/{id}") // Endpoint para atualizar um usuário pelo ID
        Call<User> updateUser(@Header("Authorization") String authorization, @Path("id") Long id, @Body User user);


        @PUT("user/password/{id}") // Endpoint para atualizar um usuário pelo ID
        Call<Void> updatePassword(@Header("Authorization") String authorization, @Path("id") Long id, @Body String password);

        @PUT("user/active/{id}") // Endpoint para ativar um usuário pelo ID
        Call<Void> activateUser(@Path("id") Long id);

        @PUT("user/desactive/{id}") // Endpoint para desativar um usuário pelo ID
        Call<Void> deactivateUser(@Path("id") Long id);

        @DELETE("user/{id}") // Endpoint para deletar um usuário pelo ID
        Call<Void> deleteUser(@Path("id") Long id);


    }
