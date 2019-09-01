package com.digitalhouse.firebaseentregavel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalhouse.firebaseentregavel.R;
import com.digitalhouse.firebaseentregavel.listener.GameListListener;
import com.digitalhouse.firebaseentregavel.model.Game;
import com.squareup.picasso.Picasso;

import java.util.List;


public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> gameList;
    private GameListListener gameListListener;

    public GamesAdapter(List<Game> gameList, GameListListener gameListListener) {
        this.gameList = gameList;
        this.gameListListener = gameListListener;
    }

    public void atualizarGames(List<Game> gameList){
        this.gameList = gameList;
        notifyDataSetChanged();
    }

    public void adicionarGames(List<Game> gameList){
        this.gameList.addAll(gameList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Game game = gameList.get(position);
        holder.bind(game);

        holder.itemView.setOnClickListener(view -> gameListListener.onGameClick(game));
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nomeTextView;
        private TextView anoTextView;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nome_text_view_id);
            anoTextView = itemView.findViewById(R.id.data_text_view_id);
            imageView = itemView.findViewById(R.id.imageView_id);


        }

        public void bind(Game game) {
            nomeTextView.setText(game.getNome());
            anoTextView.setText(game.getAno());

            Picasso.get().load(game.getImage()).into(imageView);


        }
    }
}
