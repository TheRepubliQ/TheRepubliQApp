package br.edu.ifsp.tcc.apprepublic.Api;

import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface RequestService {

    @POST("/requests")
    Call<Request> createRequest(@Header("Authorization") String authorization, @Body Request request);

    @DELETE("/requests/{id}")
    Call<Void> deleteRequest(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/requests/{id}")
    Call<Request> getRequestById(@Header("Authorization") String authorization, @Path("id") Long id);

    @GET("/requests/user/{userId}")
    Call<List<Request>> getRequestsByOwnerId(@Header("Authorization") String authorization, @Path("userId") Long userId);

    @GET("/requests/home/{homeId}")
    Call<List<Request>> getRequestsByHomeId(@Header("Authorization") String authorization, @Path("homeId") Long homeId);

    @GET("/requests/prop/{userPropId}")
    Call<List<Request>> getRequestsByUserPropId(@Header("Authorization") String authorization, @Path("userPropId") Long userPropId);

    @GET("/requests/homeInfos/{homeId}")
    Call<HomeEntity> getHomeInfoById(@Header("Authorization") String authorization, @Path("homeId") Long homeId);
}
