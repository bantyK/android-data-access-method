package com.banty.superheroes.data.remote;

import com.banty.superheroes.data.local.Superhero;

import java.util.List;

public interface RemoteDataLoaderCallback {
    void dataLoaded(List<Superhero> superheroes);

    void dataLoadingFailed(String message);
}
