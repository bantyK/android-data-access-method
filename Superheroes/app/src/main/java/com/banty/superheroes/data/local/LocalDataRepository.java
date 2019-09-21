package com.banty.superheroes.data.local;

import com.banty.superheroes.data.DataRepository;

import java.util.List;

public class LocalDataRepository implements DataRepository {

    private final DatabaseManager databaseManager;

    public LocalDataRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void getSuperheroes(DataRepositoryCallback callback) {
        List<Superhero> superheroes = databaseManager.getAll();
        if (superheroes == null || superheroes.isEmpty()) {
            callback.superheroesFailedToLoad("Superhero database is empty");
        } else {
            callback.superheroesLoaded(superheroes);
        }
    }

    @Override
    public void saveSuperheroes(List<Superhero> superheroes) {
        databaseManager.saveAll(superheroes);
    }

    @Override
    public void refreshCache() {
        // NO OP //
    }

    @Override
    public void getSuperheroById(Long id, SingleItemCallback callback) {
        Superhero superheroById = databaseManager.getSuperheroById(id);
        if (superheroById != null) {
            callback.superheroLoaded(superheroById);
        } else {
            callback.superheroFailedToLoad("Superhero not found with id : " + id);
        }
    }
}
