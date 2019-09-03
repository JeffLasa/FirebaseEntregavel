package com.digitalhouse.firebaseentregavel.modules.CadastroGame.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.digitalhouse.firebaseentregavel.R;

public class CadastroGameActivity extends AppCompatActivity {

    String nomeGame;
    String anoGame ;
    String deckGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_game);


        Intent intent = getIntent();
        if (intent!=null){
            Bundle bundle = getIntent().getExtras();
            if (bundle !=null){
                nomeGame = bundle.getString("GAME");
                anoGame = bundle.getString("ANO");
                deckGame = bundle.getString("DECK");
                TextView nomeGameTextView = findViewById(R.id.cadastro_input_text_nome_id);
                TextView anoGameTextView = findViewById(R.id.cadastro_ano_input_text_id);
                TextView deckGameTextView = findViewById(R.id.cadastro_deck_input_text_id);

                nomeGameTextView.setText(nomeGame);
                anoGameTextView.setText(anoGame);
                deckGameTextView.setText(deckGame);


            }
        }

    }
}
