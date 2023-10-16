package br.edu.ifsp.tcc.apprepublic.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTService {
    private static final String BASE_URL = "http://localhost:8080";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static HomeService getHomeService() {
        return retrofit.create(HomeService.class);
    }

    public static UserService getUserService() {
        return retrofit.create(UserService.class);
    }


}