package com.banty.superheroes.data.local;

import android.database.Cursor;

import androidx.annotation.Nullable;

import com.banty.superheroes.data.local.room.BaseDao;
import com.banty.superheroes.data.local.room.SuperheroRoomDao;

import java.util.ArrayList;
import java.util.List;

public class RoomDatabaseManager implements DatabaseManager {

    private final BaseDao superheroDao;

    public RoomDatabaseManager(BaseDao superheroDao) {
        this.superheroDao = superheroDao;
    }

    @Override
    public List<Superhero> getAll() {
        Cursor cursor = superheroDao.getAllSuperheros();
        List<Superhero> superheroList = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Superhero superhero = Superhero.getSuperheroFromCursor(cursor);
                superheroList.add(superhero);
            }
        }

        return superheroList;
    }

    @Override
    public void saveAll(List<Superhero> superheroes) {
        superheroDao.saveAll(superheroes);
    }

    @Nullable
    @Override
    public Superhero getSuperheroById(long id) {
        Cursor cursor = superheroDao.getSuperheroById(id);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            return Superhero.getSuperheroFromCursor(cursor);
        }
        return null;
    }

    @Override
    public void saveSuperhero(Superhero superhero) {
        superheroDao.insertSuperhero(superhero);
    }

    @Override
    public void updateSuperhero(Superhero newData) {
        superheroDao.updateSuperhero(newData);
    }

    @Override
    public void clear() {
        superheroDao.clearTable();
    }
}
