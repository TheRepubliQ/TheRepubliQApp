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
    @GET("user") // Endpoint para listar todos os usuários
    Call<List<User>> listUsers();

    @POST("user") // Endpoint para criar um novo usuário
    Call<User> createUser(@Body User user);

    @GET("user/{id}") // Endpoint para buscar um usuário pelo ID
    Call<User> getUserById(@Path("id") Long id);

    @GET("user/{login}") // Endpoint para buscar um usuário pelo ID
    Call<User> getUserByLogin(@Header("Authorization") String authorization, @Path("login") String login);
    @PUT("user/{id}") // Endpoint para atualizar um usuário pelo ID
    Call<User> updateUser(@Path("id") Long id, @Body User user);

    @DELETE("user/{id}") // Endpoint para deletar um usuário pelo ID
    Call<Void> deleteUser(@Path("id") Long id);

    @PUT("user/{id}/active") // Endpoint para ativar um usuário pelo ID
    Call<Void> activateUser(@Path("id") Long id);

    @PUT("user/{id}/desactive") // Endpoint para desativar um usuário pelo ID
    Call<Void> deactivateUser(@Path("id") Long id);

    @GET("user/testConnection") // Endpoint para testar a conexão com a API
    Call<String> testConnection();
}
