package android.eservices.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.GameViewHolder> {


    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView iconImageView;
        private ImageButton infoButton;
        private ImageButton gameButton;
        private CheckBox selectionCheckbox;
        private View v;
        private GameViewModel gameViewModel;
        private int position;
private GameActionInterface gameActionInterface;


        public GameViewHolder(View v, final GameActionInterface gameActionInterface) {
            super(v);
            this.v = v;
            titleTextView = v.findViewById(R.id.title_textview);
            descriptionTextView = v.findViewById(R.id.description_textview);
            iconImageView = v.findViewById(R.id.icon_imageview);
            infoButton = v.findViewById(R.id.info_button);
            selectionCheckbox = v.findViewById(R.id.selection_checkbox);
            gameButton = v.findViewById(R.id.game_button);
            this.gameActionInterface = gameActionInterface;
            setupListeners();

        }

        private void setupListeners(){
            infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });
            gameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameClicked(gameViewModel.getTitle());
                }
            });
        }

        void bind(GameViewModel gameViewModel, int position, boolean isSelectionMode, boolean isSelected) {
            System.out.println("Selemode ? " + isSelectionMode + " is selected ? " + isSelected);
            if (isSelectionMode) {
                infoButton.setVisibility(View.GONE);
                selectionCheckbox.setVisibility(View.VISIBLE);
                selectionCheckbox.setChecked(isSelected);
            } else {
                infoButton.setVisibility(View.VISIBLE);
                selectionCheckbox.setVisibility(View.GONE);
            }
            this.gameViewModel = gameViewModel;
            this.position = position;

            titleTextView.setText(gameViewModel.getTitle());
            descriptionTextView.setText(gameViewModel.getDescription());
            Glide.with(v)
                    .load(gameViewModel.getImageUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(iconImageView);

        }

        public ItemDetailsLookup.ItemDetails<String> getItemDetails() {
            return new ItemDetailsLookup.ItemDetails<String>() {
                @Override
                public int getPosition() {
                    return position;
                }

                @Nullable
                @Override
                public String getSelectionKey() {
                    return gameViewModel.getUuid();
                }
            };
        }
    }

    private List<GameViewModel> gameViewModelList;
    private SelectionTracker<String> selectionTracker;
    private boolean isSelectionMode;
private GameActionInterface gameActionInterface;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyDataAdapter(GameActionInterface gameActionInterface) {
        gameViewModelList = new ArrayList<>();
        this.gameActionInterface = gameActionInterface;
    }

    public void bindViewModels(List<GameViewModel> gameViewModelList) {
        this.gameViewModelList.clear();
        this.gameViewModelList.addAll(gameViewModelList);
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        GameViewHolder gameViewHolder = new GameViewHolder(v, gameActionInterface);
        return gameViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        holder.bind(gameViewModelList.get(position),
                position,
                isSelectionMode,
                selectionTracker.isSelected(gameViewModelList.get(position).getUuid()));
    }

    public List<GameViewModel> getGameViewModelList() {
        return gameViewModelList;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return gameViewModelList.size();
    }

    public void setSelectionTracker(final SelectionTracker<String> selectionTracker) {
        this.selectionTracker = selectionTracker;
        selectionTracker.addObserver(new SelectionTracker.SelectionObserver() {
            @Override
            public void onItemStateChanged(@NonNull Object key, boolean selected) {
                super.onItemStateChanged(key, selected);
                boolean previousState = isSelectionMode;
                isSelectionMode = !selectionTracker.getSelection().isEmpty();
                if(previousState != isSelectionMode){
                    notifyDataSetChanged();
                }
            }
        });
    }
}