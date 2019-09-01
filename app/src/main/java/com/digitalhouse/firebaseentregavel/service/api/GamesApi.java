package com.digitalhouse.firebaseentregavel.service.api;

import com.digitalhouse.firebaseentregavel.model.GameResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GamesApi {
    @GET("games")
   Observable<GameResponse> getGames(@Query("api_key") String apiKey,
                                     @Query("format") String format,
                                     @Query("sort") String sort,
                                     @Query("limit") int limit,
                                     @Query("offset") int offset);

}
