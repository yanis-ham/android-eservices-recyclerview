package android.eservices.recyclerview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GameActionInterface {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar toolbar;
    private CoordinatorLayout coordinatorLayout;
    private GameAdapter gameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        setupRecyclerView();


    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.my_recyclerview);
        layoutManager = new LinearLayoutManager(this);

        List<GameViewModel> gameViewModelList = DataGenerator.generateData();

        gameAdapter = new GameAdapter(this);
        gameAdapter.bindGameViewModelList(gameViewModelList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(gameAdapter);



    }

    public void displaySnackBar(String message) {
        //TODO write a method that displays a snackbar in the coordinator layout with the "message" parameter as content.
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onGameInfoClicked(String gameTitle) {
        Log.e("MainActivity","On Game Info Click: " + gameTitle);
        displaySnackBar(getString(R.string.game_info_clicked, gameTitle));
    }

    @Override
    public void onGameClicked(String gameTitle) {
        Log.e("MainActivity","On Game Title Click: " + gameTitle);
        displaySnackBar(getString(R.string.game_clicked, gameTitle));

    }

    //TODO create callback methods for item click
    //Use ressource strings to get the text to display

}
