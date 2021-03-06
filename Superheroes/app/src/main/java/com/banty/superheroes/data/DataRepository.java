package com.banty.superheroes.data;

import com.banty.superheroes.data.local.Superhero;

import java.util.List;

public interface DataRepository {

    public interface DataRepositoryCallback {

        void superheroesLoaded(List<Superhero> superheroes);

        void superheroesFailedToLoad(String message);
    }

    public interface SingleItemCallback {
        void superheroLoaded(Superhero superhero);

        void superheroFailedToLoad(String message);
    }


    void getSuperheroes(DataRepositoryCallback callback);

    void saveSuperheroes(List<Superhero> superheroes);

    void refreshCache();

    void getSuperheroById(Long id, SingleItemCallback callback);

}


