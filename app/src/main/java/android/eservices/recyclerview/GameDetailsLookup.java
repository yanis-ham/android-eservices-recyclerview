package android.eservices.recyclerview;

import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class GameDetailsLookup extends ItemDetailsLookup<String> {

    private final RecyclerView mRecyclerView;

    GameDetailsLookup(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public ItemDetails<String> getItemDetails(MotionEvent e) {
        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(view);
            if (holder instanceof MyDataAdapter.GameViewHolder) {
                return ((MyDataAdapter.GameViewHolder) holder).getItemDetails();
            }
        }
        return null;
    }
}


