package br.edu.ifsp.tcc.apprepublic.Api;

import br.edu.ifsp.tcc.apprepublic.model.user.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TestService {
    @POST("registrar")
    Call<User> cadastrarUser(@Body User user);

}

