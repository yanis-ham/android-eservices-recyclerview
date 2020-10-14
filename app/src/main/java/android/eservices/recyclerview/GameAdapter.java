package android.eservices.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>  {

    private List<GameViewModel> gameViewModelList;
    private GameActionInterface gameActionInterface;

    public GameAdapter(MainActivity mainActivity) {
        this.gameActionInterface = mainActivity;
    }

    @NonNull
    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent,false);
        return new GameViewHolder(v, gameActionInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.GameViewHolder holder, int position) {
        holder.Bind(gameViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.gameViewModelList.size();
    }

    public void bindGameViewModelList(List<GameViewModel> gameViewModelList) {
        this.gameViewModelList = gameViewModelList;
        notifyDataSetChanged();
    }


    public static class GameViewHolder extends RecyclerView.ViewHolder {

        private TextView gameNameTextView;
        private TextView gameDescriptionTextView;
        private ImageView imageIconView;
        private View cardView;
        private ImageButton gameImageButton;
        private ImageButton gameInfoImageButton;
        private GameActionInterface gameActionInterface;



        public GameViewHolder(View cardView, final GameActionInterface gameActionInterface) {
            super(cardView);
            this.cardView = cardView;
            gameNameTextView = cardView.findViewById(R.id.title_textview);
            gameDescriptionTextView = cardView.findViewById(R.id.description_textview);
            imageIconView = cardView.findViewById(R.id.icon_imageview);
            gameImageButton = cardView.findViewById(R.id.game_button);
            gameInfoImageButton = cardView.findViewById(R.id.info_button);
            this.gameActionInterface = gameActionInterface;


            }

        public void Bind(final GameViewModel gameViewModel){
            gameNameTextView.setText(gameViewModel.getTitle());
            gameDescriptionTextView.setText(gameViewModel.getDescription());
            Glide.with(cardView).load(gameViewModel.getImageUrl()).into(imageIconView);

            gameImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameActionInterface.onGameClicked(gameViewModel.getTitle());
                }
            });

            gameInfoImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });
        }
    }
}
