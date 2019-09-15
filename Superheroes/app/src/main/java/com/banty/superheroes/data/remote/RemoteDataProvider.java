package com.banty.superheroes.data.remote;

public interface RemoteDataProvider {

    void getDataFromServer(RemoteDataLoaderCallback callback);

    void dataFailedToLoad(String message);
}
