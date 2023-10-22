    package br.edu.ifsp.tcc.apprepublic.Api;
    
    import java.io.IOException;
    
    import retrofit2.Call;
    import retrofit2.Response;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;
    
    public class RESTService {
        private static final String BASE_URL = "https://192.168.0.19:8080/";
    
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
    
        public static TestService getTestService(){ return retrofit.create(TestService.class);}

        public static AuthService getAuthService(){return  retrofit.create(AuthService.class);}
    }
