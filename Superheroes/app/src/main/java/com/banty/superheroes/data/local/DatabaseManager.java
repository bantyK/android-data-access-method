package com.banty.superheroes.data.local;

import java.util.List;

public interface DatabaseManager {

    List<Superhero> getAll();

    void saveAll(List<Superhero> superheroes);

    Superhero getSuperheroById(long id);

    void saveSuperhero(Superhero superhero);

    void updateSuperhero(Superhero newData);

    void clear();
}
