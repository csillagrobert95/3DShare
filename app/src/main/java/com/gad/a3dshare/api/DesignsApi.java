package com.gad.a3dshare.api;

import com.gad.a3dshare.api.model.Design;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DesignsApi {
    @GET("csillagrobert95/3DShare/master/JSON/{fileName}")
    Call<List<Design>> getDesigns(@Path("fileName") String jsonToFetch);
}
