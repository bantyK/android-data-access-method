package com.banty.superheroes.data.local.room;

import android.database.Cursor;

import com.banty.superheroes.data.local.Superhero;

import java.util.List;

public interface BaseDao {

    Cursor getAllSuperheros();

    Cursor getSuperheroById(long id);

    long insertSuperhero(Superhero superhero);

    long[] saveAll(List<Superhero> superheroes);

    int updateSuperhero(Superhero superhero);

    void clearTable();
}
