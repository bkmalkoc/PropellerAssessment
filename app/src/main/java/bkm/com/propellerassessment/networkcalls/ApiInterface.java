package bkm.com.propellerassessment.networkcalls;

import bkm.com.propellerassessment.model.UserData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("ph-svc-mobile-interview-jyzi2gyja/propeller_mobile_assessment_data.json")
    Call<UserData> getData();
}
