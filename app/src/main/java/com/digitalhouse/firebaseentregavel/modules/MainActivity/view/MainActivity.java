package com.digitalhouse.firebaseentregavel.modules.MainActivity.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.digitalhouse.firebaseentregavel.R;
import com.digitalhouse.firebaseentregavel.adapter.GamesAdapter;
import com.digitalhouse.firebaseentregavel.listener.GameListListener;
import com.digitalhouse.firebaseentregavel.model.Game;
import com.digitalhouse.firebaseentregavel.modules.CadastroGame.view.CadastroGameActivity;
import com.digitalhouse.firebaseentregavel.modules.DetalhesGame.DetalheGameActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GameListListener {

    public static final int LIMIT = 20;
    private int offset;
    private static final String TAG = "MainActivity";

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Game> gameList= new ArrayList<>();
    private RecyclerView gameRecyclerView;
    private SearchView searchViewPesquisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.detalhe_editar_button_id);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastraGame();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_id);
        searchViewPesquisa = findViewById(R.id.search_view_id);
        searchViewPesquisa.setQueryHint("Buscar Game");

        searchViewPesquisa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.d("onQueryTextSubmit", "Texto digitado : " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String textoDigitado = newText.toUpperCase();
                pesquisarGames(textoDigitado);

                Log.d("onQueryTextChange", "Texto digitado : " + newText);
                return true;
            }
        });



        gameRecyclerView = findViewById(R.id.games_reycler_view_id);
        GamesAdapter gamesAdapter = new GamesAdapter (gameList,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gameRecyclerView.setAdapter(gamesAdapter);
        gameRecyclerView.setLayoutManager(gridLayoutManager);


        Game game1 = new Game();
        game1.setNome("Pac-Man asjjahsdj asfafaffa asdasdasda asa asasda asfdhgfghf");
        game1.setDeck("Arcade game sucesso nos anos 80");
        game1.setAno("1982");
        game1.setImage("https://www.giantbomb.com/api/image/scale_large/707238-bass_avenger.jpg");



        Game game2 = new Game();
        game2.setNome("River Ride");
        game2.setDeck("Arcade de Avioes de guerra. Um dos primeiros jogos e  grande sucesso nos anos 80");
        game2.setAno("1985");
        game2.setImage("https://www.giantbomb.com/api/image/scale_large/707365-fritz_9_play_chess.jpg");


        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game2);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game1);
        gameList.add(game2);
        gameList.add(game2);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game1);
        gameList.add(game1);



//        GameViewModel gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
//        gameViewModel.atualizarGames(LIMIT, offset);
//        gameViewModel.getGameLiveData()
//                .observe(this, gameList -> {
//                    gamesAdapter.adicionarGames(gameList);
//                });

//        swipeRefreshLayout.setOnRefreshListener(() -> gameViewModel.atualizarGames(LIMIT, offset));
//
//        gameRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if (gameRecyclerView.canScrollVertically(1)) {
//
//                    offset += LIMIT;
//
//                    gameViewModel.atualizarGames(LIMIT, offset);
//
//                }
//
//            }
//
//        });
    }



    private void pesquisarGames(String textoDigitado) {

//        gameList.clear();
//        if(textoDigitado.length() > 0)


    }

    private void cadastraGame() {
        Intent intent = new Intent(this,CadastroGameActivity.class);
        startActivity(intent);
    }


    @Override
    public void onGameClick(Game game) {

        Intent intent = new Intent(this, DetalheGameActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("GAME", game.getNome());
        bundle.putString("ANO",game.getAno());
        bundle.putString("DECK",game.getDeck());

        intent.putExtras(bundle);

        startActivity(intent);

    }
}

