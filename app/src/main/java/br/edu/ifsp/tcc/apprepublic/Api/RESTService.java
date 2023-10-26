    package br.edu.ifsp.tcc.apprepublic.Api;
    

    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;
    import okhttp3.OkHttpClient;
    import okhttp3.CertificatePinner;
    
    public class RESTService {
        private static final String BASE_URL = "http://192.168.0.19:8080/";

        static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .hostnameVerifier((hostname, session) -> true)  // Desabilitar a verificação do nome do host
                .certificatePinner(new CertificatePinner.Builder().build())  // Ignorar a verificação do certificado
                .build();

        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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
