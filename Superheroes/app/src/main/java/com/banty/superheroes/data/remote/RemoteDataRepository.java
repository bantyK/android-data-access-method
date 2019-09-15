package com.banty.superheroes.data.remote;

import com.banty.superheroes.data.DataRepository;
import com.banty.superheroes.data.local.Superhero;

import java.util.List;

public class RemoteDataRepository implements DataRepository {


    @Override
    public void getSuperheroes(final DataRepositoryCallback callback) {
        new RetrofitDataProvider(RetrofitClient.getRetrofitClient().create(SuperheroApi.class))
                .getDataFromServer(new RemoteDataLoaderCallback() {
                    @Override
                    public void dataLoaded(List<Superhero> superheroes) {
                        callback.superheroesLoaded(superheroes);
                    }

                    @Override
                    public void dataLoadingFailed(String message) {
                        callback.superheroesFailedToLoad(message);
                    }
                });
    }

    @Override
    public void saveSuperheroes(List<Superhero> superheroes) {
        // this will be handled by the local repository
    }

    @Override
    public void refreshCache() {
        // handled by the super method
    }
}

