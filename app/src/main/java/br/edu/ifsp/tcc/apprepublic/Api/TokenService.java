package br.edu.ifsp.tcc.apprepublic.Api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TokenService {
    @DELETE("tokens/revoke")
    Call<Void> revokeToken(@Header("Authorization") String authorization);
}
