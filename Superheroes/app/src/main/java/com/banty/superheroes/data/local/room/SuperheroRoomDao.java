package com.banty.superheroes.data.local.room;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.banty.superheroes.data.local.Superhero;

import java.util.List;

@Dao
public interface SuperheroRoomDao extends BaseDao {

    @Override
    @Query("SELECT * FROM " + Superhero.TABLE_NAME)
    Cursor getAllSuperheros();

    @Override
    @Query("SELECT * FROM " + Superhero.TABLE_NAME + " WHERE " + Superhero.COLUMN_ID + " = :id")
    Cursor getSuperheroById(long id);

    @Override
    @Insert
    long insertSuperhero(Superhero superhero);

    @Override
    @Insert
    long[] saveAll(List<Superhero> superheroes);

    @Override
    @Update
    int updateSuperhero(Superhero superhero);

    @Override
    @Query("DELETE FROM " + Superhero.TABLE_NAME)
    void clearTable();
}
