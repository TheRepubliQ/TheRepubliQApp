package br.edu.ifsp.tcc.apprepublic.Api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TestService {
    @GET("test")
    Call<String> testEndpoint();
}

