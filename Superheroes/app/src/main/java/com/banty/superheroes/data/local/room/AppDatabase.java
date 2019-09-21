package com.banty.superheroes.data.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.banty.superheroes.data.local.Superhero;

@Database(entities = {Superhero.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SuperheroRoomDao superheroDao();

    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return appDatabase;
    }

}
