package com.banty.superheroes.data.local;

import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = Superhero.TABLE_NAME)
public class Superhero {

    public static final String TABLE_NAME = "cheeses";

    //Data Column names
    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE_URL = "image";
    public static final String COLUMN_FRANCHISE = "franchise";
    public static final String COLUMN_REAL_NAME = "real_name";
    public static final String COLUMN_PLAYED_BY = "played_by";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    public long id;

    @ColumnInfo(name = COLUMN_NAME)
    @SerializedName("name")
    public String name;

    @SerializedName("image_url")
    @ColumnInfo(name = COLUMN_IMAGE_URL)
    public String imageUrl;

    @SerializedName("franchise")
    @ColumnInfo(name = COLUMN_FRANCHISE)
    public String franchise;

    @SerializedName("real_name")
    @ColumnInfo(name = COLUMN_REAL_NAME)
    public String realName;

    @SerializedName("played_by")
    @ColumnInfo(name = COLUMN_PLAYED_BY)
    public String playedBy;


    public static Superhero getHeroFromContentValue(@NonNull ContentValues values) {

        final Superhero superhero = new Superhero();

        if (values.containsKey(COLUMN_ID)) {
            superhero.id = values.getAsLong(COLUMN_ID);
        }

        if (values.containsKey(COLUMN_NAME)) {
            superhero.name = values.getAsString(COLUMN_NAME);
        }

        if (values.containsKey(COLUMN_IMAGE_URL)) {
            superhero.imageUrl = values.getAsString(COLUMN_IMAGE_URL);
        }

        if (values.containsKey(COLUMN_FRANCHISE)) {
            superhero.franchise = values.getAsString(COLUMN_FRANCHISE);
        }

        if (values.containsKey(COLUMN_REAL_NAME)) {
            superhero.realName = values.getAsString(COLUMN_REAL_NAME);
        }


        if (values.containsKey(COLUMN_PLAYED_BY)) {
            superhero.playedBy = values.getAsString(COLUMN_PLAYED_BY);
        }

        return superhero;
    }

}
