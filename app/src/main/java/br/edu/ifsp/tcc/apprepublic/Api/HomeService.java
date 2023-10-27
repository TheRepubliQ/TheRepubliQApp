package br.edu.ifsp.tcc.apprepublic.Api;

import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface HomeService {
    @GET("home") // Endpoint para listar todas as casas
    Call<List<HomeEntity>> listHomes(@Header("Authorization") String authorization);

    @POST("home") // Endpoint para criar uma nova casa
    Call<HomeEntity> createHome(@Header("Authorization") String authorization, @Body HomeEntity home);

    @GET("home/{id}") // Endpoint para buscar uma casa pelo ID
    Call<HomeEntity> getHomeById(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("home/{user_id}") // Endpoint para buscar uma casa pelo ID
    Call<List<HomeEntity>> listHomesByUserId(@Header("Authorization") String authorization, @Path("user_id") Long id);

    @PUT("home/{id}") // Endpoint para atualizar uma casa pelo ID
    Call<HomeEntity> updateHome(@Header("Authorization") String authorization, @Path("id") Long id, @Body HomeEntity home);

    @DELETE("home/{id}")  // Endpoint para deletar uma casa pelo ID
    Call<Void> deleteHome(@Header("Authorization") String authorization, @Path("id") Long id);
}
