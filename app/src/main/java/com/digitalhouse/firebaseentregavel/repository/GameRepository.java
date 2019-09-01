package com.digitalhouse.firebaseentregavel.repository;


import com.digitalhouse.firebaseentregavel.model.Game;
import com.digitalhouse.firebaseentregavel.service.api.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class GameRepository {

    private RetrofitService retrofitService = new RetrofitService();

    private static final String API_KEY = "0556ae023fc3906df97a9dc76ea920b2556056dd";
    private static final String FORMAT = "json";
    private static final String SORT = "id:desc";

    public Observable<List<Game>> getGameList(){
        return Observable.create(new ObservableOnSubscribe<List<Game>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Game>> emitter) throws Exception {

                List<Game> gameList = new ArrayList<>();

                Game game = new Game();
                game.setNome("PacMan");
                game.setDeck("jogo arcade famoso");
                game.setAno("1985");

                gameList.add(game);

                emitter.onNext(gameList);
                emitter.onComplete();

            }
        });
    }

    public Observable<List<Game>> getGameListApi(int limit, int offset){
        return retrofitService.getGamesApi()
                .getGames(API_KEY, FORMAT, SORT, limit, offset)
                .map(gameResponse -> gameResponse.getResults());
    }

}
