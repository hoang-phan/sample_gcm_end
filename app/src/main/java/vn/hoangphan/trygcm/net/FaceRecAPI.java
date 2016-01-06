package vn.hoangphan.trygcm.net;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import vn.hoangphan.trygcm.models.CreateGcmKeyRequest;

/**
 * Created by Hoang Phan on 9/12/2015.
 */
public interface FaceRecAPI {
    @Headers("Content-Type: application/json")
    @POST("/gcms")
    void createGcmKey(@Body CreateGcmKeyRequest request, Callback<Response> callback);
}
