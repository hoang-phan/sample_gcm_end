package vn.hoangphan.trygcm.net;

import android.util.Log;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.hoangphan.trygcm.constants.Constants;
import vn.hoangphan.trygcm.models.CreateGcmKeyRequest;

/**
 * Created by Hoang Phan on 9/12/2015.
 */
public class APIService {
    private static FaceRecAPI instance;

    public static FaceRecAPI getInstance() {
        if (instance == null) {
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.API_ENDPOINT).setConverter(new JSONConverter()).build();
            instance = restAdapter.create(FaceRecAPI.class);
        }
        return instance;
    }

    public static void updateGcmId(String gcmRegId) {
        try {
            getInstance().createGcmKey(new CreateGcmKeyRequest(gcmRegId), new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    Log.d("updateGcmId", "success");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("updateGcmId", "error");
                }
            });
        } catch (Exception e) {
            Log.e("error", e.toString());
        }
    }
}
