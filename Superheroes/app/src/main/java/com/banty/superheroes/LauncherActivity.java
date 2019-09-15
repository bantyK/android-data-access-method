package com.banty.superheroes;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.banty.superheroes.data.DataRepository;
import com.banty.superheroes.data.DataRepositoryImpl;
import com.banty.superheroes.data.local.LocalDataRepository;
import com.banty.superheroes.data.local.Superhero;
import com.banty.superheroes.data.remote.RemoteDataRepository;

import java.util.List;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RemoteDataRepository remote = new RemoteDataRepository();
        LocalDataRepository local = new LocalDataRepository();
        DataRepository dataRepository = new DataRepositoryImpl(remote, local);

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
