package br.edu.ifsp.tcc.apprepublic.Api;

import br.edu.ifsp.tcc.apprepublic.security.TokenResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {
    @FormUrlEncoded
    @POST("oauth/token") // Verifique se a URL do endpoint está correta
    Call<TokenResponse> authenticate(
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType
    );
}
