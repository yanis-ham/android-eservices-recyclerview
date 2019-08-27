package android.eservices.recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

import java.util.List;

public class GameItemKeyProvider extends ItemKeyProvider<String> {

    private MyDataAdapter myDataAdapter;

    public GameItemKeyProvider(int scope, MyDataAdapter myDataAdapter) {
        super(scope);
        this.myDataAdapter = myDataAdapter;
    }

    @Nullable
    @Override
    public String getKey(int position) {
        return myDataAdapter.getGameViewModelList().get(position).getUuid();
    }

    @Override
    public int getPosition(@NonNull String key) {
        for(GameViewModel gameViewModel : myDataAdapter.getGameViewModelList()){
            if(key.equals(gameViewModel.getUuid())){
                return myDataAdapter.getGameViewModelList().indexOf(gameViewModel);
            }
        }
        return -1;
    }
}
