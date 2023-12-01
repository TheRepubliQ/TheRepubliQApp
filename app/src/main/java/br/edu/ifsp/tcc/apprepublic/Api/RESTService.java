package br.edu.ifsp.tcc.apprepublic.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTService {
    private static final String BASE_URL = "http://192.168.0.19:8080/";

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")  // Formato esperado pelo Jackson
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))  // Use a instância do Gson configurada
            .build();

    public static HomeService getHomeService() {
        return retrofit.create(HomeService.class);
    }

    public static HomeViewService getHomeViewService() {return retrofit.create(HomeViewService.class);
    }

    public static RequestService getRequestService(){return retrofit.create(RequestService.class);}

    public static UserService getUserService() {
        return retrofit.create(UserService.class);
    }

    public static TestService getTestService() {
        return retrofit.create(TestService.class);
    }

    public static AuthService getAuthService() {
        return retrofit.create(AuthService.class);
    }

    public static TokenService getTokenService() {return retrofit.create(TokenService.class);
    }

}
