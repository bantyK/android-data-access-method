package com.banty.superheroes.data;

import android.util.Log;

import androidx.annotation.NonNull;

import com.banty.superheroes.data.local.LocalDataRepository;
import com.banty.superheroes.data.local.Superhero;
import com.banty.superheroes.data.remote.RemoteDataRepository;

import java.util.List;

public class DataRepositoryImpl implements DataRepository {

    private final DataRepository remoteDataRepository;
    private final DataRepository localDataRepository;


    private boolean isCacheDirty = false;

    public DataRepositoryImpl(RemoteDataRepository remoteDataRepository, LocalDataRepository localDataRepository) {
        this.remoteDataRepository = remoteDataRepository;
        this.localDataRepository = localDataRepository;
    }

    @Override
    public void getSuperheroes(final DataRepositoryCallback callback) {
        if (isCacheDirty) {
            fetchDataFromRemote(callback);
        }
        localDataRepository.getSuperheroes(new DataRepositoryCallback() {
            @Override
            public void superheroesLoaded(List<Superhero> superheroes) {
                callback.superheroesLoaded(superheroes);
            }

            @Override
            public void superheroesFailedToLoad(String message) {
                Log.d("Tag##", "Data not present in local storage");
                fetchDataFromRemote(callback);
            }
        });
    }

    private void fetchDataFromRemote(final DataRepositoryCallback callback) {
        remoteDataRepository.getSuperheroes(new DataRepositoryCallback() {
            @Override
            public void superheroesLoaded(List<Superhero> superheroes) {
                saveSuperheroes(superheroes);
                callback.superheroesLoaded(superheroes);
                isCacheDirty = false;
            }

            @Override
            public void superheroesFailedToLoad(String message) {
                Log.d("Tag##", "Failed to load data from remote server");
                localDataRepository.getSuperheroes(callback);
            }
        });
    }

    @Override
    public void saveSuperheroes(List<Superhero> superheroes) {
        Log.d("Tag##", "Saving data into local");
        localDataRepository.saveSuperheroes(superheroes);
    }

    @Override
    public void refreshCache() {
        isCacheDirty = true;
    }

    @Override
    public void getSuperheroById(Long id, final SingleItemCallback callback) {
        localDataRepository.getSuperheroById(id, new SingleItemCallback() {
            @Override
            public void superheroLoaded(Superhero superhero) {
                callback.superheroLoaded(superhero);
            }

            @Override
            public void superheroFailedToLoad(String message) {
                callback.superheroFailedToLoad(message);
            }
        });
    }
}
