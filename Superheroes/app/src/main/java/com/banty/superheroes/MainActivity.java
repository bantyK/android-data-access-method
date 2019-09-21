package com.banty.superheroes;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.banty.superheroes.DI.DependencyInjector;
import com.banty.superheroes.data.DataRepository;
import com.banty.superheroes.data.local.Superhero;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Nullable
    private DataRepository dataRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DependencyInjector di = new DependencyInjector(this);
        dataRepository = di.provideDataRepository();

        dataRepository.getSuperheroes(new DataRepository.DataRepositoryCallback() {
            @Override
            public void superheroesLoaded(List<Superhero> superheroes) {
                Log.d("Tag##", "" + superheroes.size());
            }

            @Override
            public void superheroesFailedToLoad(String message) {
                Log.d("Tag##", message);
            }
        });
    }
}
