package com.banty.superheroes.DI;

import android.content.Context;

import com.banty.superheroes.data.DataRepository;
import com.banty.superheroes.data.DataRepositoryImpl;
import com.banty.superheroes.data.local.DatabaseManager;
import com.banty.superheroes.data.local.LocalDataRepository;
import com.banty.superheroes.data.local.RoomDatabaseManager;
import com.banty.superheroes.data.local.room.AppDatabase;
import com.banty.superheroes.data.local.room.BaseDao;
import com.banty.superheroes.data.remote.RemoteDataProvider;
import com.banty.superheroes.data.remote.RemoteDataRepository;
import com.banty.superheroes.data.remote.RetrofitClient;
import com.banty.superheroes.data.remote.RetrofitDataProvider;
import com.banty.superheroes.data.remote.SuperheroApi;

import retrofit2.Retrofit;

/**
 * Can be replaced by Dagger2
 */

public class DependencyInjector {

    private final Context context;

    public DependencyInjector(Context context) {
        this.context = context;
    }


    private Retrofit provideRetrofitClient() {
        return RetrofitClient.getRetrofitClient();
    }

    private RemoteDataProvider provideRemoteDataProvider(SuperheroApi api) {
        return new RetrofitDataProvider(api);
    }

    private RemoteDataRepository provideRemoteDataRepository(RemoteDataProvider remoteDataProvider) {
        return new RemoteDataRepository(remoteDataProvider);
    }

    private DatabaseManager provideDatabaseManager(BaseDao dao) {
        return new RoomDatabaseManager(dao);
    }

    private LocalDataRepository provideLocalDataRepository(DatabaseManager db) {
        return new LocalDataRepository(db);
    }


    private BaseDao getDao(Context context) {
        return AppDatabase.getInstance(context)
                .superheroDao();
    }

    public DataRepository provideDataRepository() {
        RemoteDataRepository remoteDataRepository = provideRemoteDataRepository(
                provideRemoteDataProvider(provideRetrofitClient().create(SuperheroApi.class)));

        LocalDataRepository localDataRepository = provideLocalDataRepository(
                provideDatabaseManager(getDao(context))
        );

        return new DataRepositoryImpl(remoteDataRepository, localDataRepository);
    }

}
