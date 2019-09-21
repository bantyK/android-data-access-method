package com.banty.superheroes.data.remote;

import com.banty.superheroes.data.DataRepository;
import com.banty.superheroes.data.local.Superhero;

import java.util.List;

public class RemoteDataRepository implements DataRepository {

    private RemoteDataProvider remoteDataProvider;

    public RemoteDataRepository(RemoteDataProvider remoteDataProvider) {
        this.remoteDataProvider = remoteDataProvider;
    }

    @Override
    public void getSuperheroes(final DataRepositoryCallback callback) {
        remoteDataProvider.getDataFromServer(new RemoteDataLoaderCallback() {
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
        // NO OP //
        // this will be handled by the local repository
    }

    @Override
    public void refreshCache() {
        // handled by the parent
    }

    @Override
    public void getSuperheroById(Long id, SingleItemCallback callback) {
        // NO OP //
    }
}

