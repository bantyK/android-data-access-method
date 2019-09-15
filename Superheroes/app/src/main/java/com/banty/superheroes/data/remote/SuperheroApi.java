package com.banty.superheroes.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperheroApi {

    @GET("bins/hlgur/")
    Call<SuperheroApiResponse> getHeroes();
}
