package com.example.networkmvvm.retrofit;

import com.example.networkmvvm.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShibeService {

    // Constants for Shibe API get method declared
    @GET(Constants.SHIBE_PATH)
    Call<List<String>> getShibes(
            @Query("count") String count,
            @Query("urls") Boolean urls,
            @Query("httpsUrls") Boolean httpsUrls
    );
}
