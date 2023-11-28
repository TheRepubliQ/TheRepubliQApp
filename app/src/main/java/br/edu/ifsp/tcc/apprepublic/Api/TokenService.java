package br.edu.ifsp.tcc.apprepublic.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenService {
    @FormUrlEncoded
    @POST("tokens/revoke")
    Call<Void> revokeToken(
            @Field("token") String token,
            @Field("token_type_hint") String tokenTypeHint
    );
}
