package com.banty.superheroes.data.remote;

import com.banty.superheroes.data.local.Superhero;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuperheroApiResponse {
    @SerializedName("data")
    public List<Superhero> superheroes;
}
