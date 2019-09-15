package com.banty.superheroes.data.local;

import com.banty.superheroes.data.DataRepository;

import java.util.List;

public class LocalDataRepository implements DataRepository {

    @Override
    public void getSuperheroes(DataRepositoryCallback callback) {
        callback.superheroesFailedToLoad("testing");
    }

    @Override
    public void saveSuperheroes(List<Superhero> superheroes) {

    }

    @Override
    public void refreshCache() {

    }
}
