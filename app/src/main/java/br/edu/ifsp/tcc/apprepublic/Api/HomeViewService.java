package br.edu.ifsp.tcc.apprepublic.Api;

import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeViewService {
    @GET("home") // Endpoint para listar todas as casas
    Call<List<HomeEntity>> listHomes();

    @GET("home/{id}") // Endpoint para buscar uma casa pelo ID
    Call<HomeEntity> getHomeById(@Path("id") Long id);
}
