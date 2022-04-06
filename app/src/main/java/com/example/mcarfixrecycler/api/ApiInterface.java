package com.example.mcarfixrecycler.api;

import com.example.mcarfixrecycler.model.ListData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("regulatorapi/")
    Call<List<ListData>> getRegulators();
}
