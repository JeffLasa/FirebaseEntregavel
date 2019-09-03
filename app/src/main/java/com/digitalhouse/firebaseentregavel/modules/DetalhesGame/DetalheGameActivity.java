package com.digitalhouse.firebaseentregavel.modules.DetalhesGame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.digitalhouse.firebaseentregavel.R;
import com.digitalhouse.firebaseentregavel.modules.CadastroGame.view.CadastroGameActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetalheGameActivity extends AppCompatActivity {

    String nomeGame;
    String anoGame ;
    String deckGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_game);

        FloatingActionButton editar = findViewById(R.id.detalhe_editar_button_id);
        editar.setOnClickListener(view -> {
            cadastraGame();
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });


        Intent intent = getIntent();
        if (intent!=null){
            Bundle bundle = getIntent().getExtras();
            if (bundle !=null){
                nomeGame = bundle.getString("GAME");
                anoGame = bundle.getString("ANO");
                deckGame = bundle.getString("DECK");
                TextView tituloGameTextView = findViewById(R.id.detalhe_Titulo_text_view_id);
                TextView nomeGameTextView = findViewById(R.id.detalhe_nome_text_view_id);
                TextView anoGameTextView = findViewById(R.id.detalhe_ano_text_view_id);
                TextView deckGameTextView = findViewById(R.id.detalhe_deck_text_view_id);

                tituloGameTextView.setText(nomeGame);
                nomeGameTextView.setText(nomeGame);
                anoGameTextView.setText(anoGame);
                deckGameTextView.setText(deckGame);


            }
        }

    }

    private void cadastraGame() {
        Intent intent = new Intent(this, CadastroGameActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("GAME",nomeGame);
        bundle.putString("ANO",anoGame);
        bundle.putString("DECK",deckGame);

        intent.putExtras(bundle);

        startActivity(intent);
    }

}
