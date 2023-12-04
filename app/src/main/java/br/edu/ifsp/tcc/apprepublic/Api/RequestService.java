package br.edu.ifsp.tcc.apprepublic.Api;

import java.util.List;

import br.edu.ifsp.tcc.apprepublic.model.home.HomeEntity;
import br.edu.ifsp.tcc.apprepublic.model.request.Request;
import br.edu.ifsp.tcc.apprepublic.model.user.User;
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
    Call<List<HomeEntity>> getRequestsByOwnerId(@Header("Authorization") String authorization, @Path("userId") Long userId);

    @GET("/requests/home/{homeId}")
    Call<List<User>> getRequestsByHomeId(@Header("Authorization") String authorization, @Path("homeId") Long homeId);

    @GET("/requests/prop/{userPropId}")
    Call<List<HomeEntity>> getRequestsByUserPropId(@Header("Authorization") String authorization, @Path("userPropId") Long userPropId);

    @GET("/requests/homeInfos/{homeId}")
    Call<HomeEntity> getHomeInfoById(@Header("Authorization") String authorization, @Path("homeId") Long homeId);

    @DELETE("/requests/home/home={homeId}/user={userId}")
    Call<Void> deleteHomeRequest(@Header("Authorization") String authorization, @Path("homeId") Long homeId, @Path("userId") Long userId);

    @GET("/requests/homeInfos/home={homeId}/user={userId}")
    Call<Request> getConfirmInterested(@Header("Authorization") String authorization, @Path("homeId") Long homeId, @Path("userId") Long userId);
}
