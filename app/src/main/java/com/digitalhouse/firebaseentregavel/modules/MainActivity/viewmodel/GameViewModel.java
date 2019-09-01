package com.digitalhouse.firebaseentregavel.modules.MainActivity.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.digitalhouse.firebaseentregavel.model.Game;
import com.digitalhouse.firebaseentregavel.repository.GameRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class GameViewModel extends AndroidViewModel {

    private MutableLiveData<List<Game>> gameLiveData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private GameRepository gameRepository = new GameRepository();

    public GameViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Game>> getGameLiveData() {
        return gameLiveData;
    }

    public void atualizarGames(int limit, int offset) {
        disposable.add(
                gameRepository.getGameListApi(limit, offset)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(games -> {
                                    List<Game> gameList = new ArrayList<>();
                                    for (Game game : gameList) {


                                    }
                                    gameLiveData.setValue(gameList);
                                },
                                throwable -> throwable.printStackTrace())
        );
    }
}
