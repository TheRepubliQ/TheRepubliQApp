package br.edu.ifsp.tcc.apprepublic.Api;

import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface HomeService {
    @GET("home") // Endpoint para listar todas as casas
    Call<List<HomeEntity>> listHomes();

    @POST("home") // Endpoint para criar uma nova casa
    Call<HomeEntity> createHome(@Body HomeEntity home);

    @GET("home/{id}") // Endpoint para buscar uma casa pelo ID
    Call<HomeEntity> getHomeById(@Path("id") Long id);

    @PUT("home/{id}") // Endpoint para atualizar uma casa pelo ID
    Call<HomeEntity> updateHome(@Path("id") Long id, @Body HomeEntity home);

    @DELETE("home/{id}") // Endpoint para deletar uma casa pelo ID
    Call<Void> deleteHome(@Path("id") Long id);
}