package com.banty.superheroes.data.remote;

import androidx.annotation.Nullable;

import com.banty.superheroes.data.local.Superhero;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitDataProvider implements RemoteDataProvider {

    private final SuperheroApi api;

    public RetrofitDataProvider(SuperheroApi api) {
        this.api = api;
    }


    @Override
    public void getDataFromServer(final RemoteDataLoaderCallback callback) {
        api.getHeroes().enqueue(new Callback<SuperheroApiResponse>() {
            @Override
            public void onResponse(@Nullable Call<SuperheroApiResponse> call, @Nullable Response<SuperheroApiResponse> response) {
                if (response != null && response.body() != null) {
                    List<Superhero> superheroes = response.body().superheroes;
                    if (superheroes == null || superheroes.size() == 0) {
                        callback.dataLoadingFailed("Empty response");
                    } else {
                        callback.dataLoaded(superheroes);
                    }
                } else {
                    callback.dataLoadingFailed("Response is null from API");
                }

            }

            @Override
            public void onFailure(Call<SuperheroApiResponse> call, Throwable t) {
                String message = t.getMessage();
                callback.dataLoadingFailed(message);
                dataFailedToLoad(message);
            }
        });

    }

    @Override
    public void dataFailedToLoad(String message) {

    }
}
