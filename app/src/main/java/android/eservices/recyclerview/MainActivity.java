package android.eservices.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.OnItemActivatedListener;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements ActionMode.Callback, GameActionInterface {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyDataAdapter myDataAdapter;
    private Toolbar toolbar;
    private SelectionTracker<String> selectionTracker;
    private ActionMode actionMode;
    private CoordinatorLayout coordinatorLayout;

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
        recyclerView = (RecyclerView) findViewById(R.id.my_recyclerview);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutManager instanceof GridLayoutManager) {
                    layoutManager = new LinearLayoutManager(MainActivity.this);
                } else {
                    layoutManager = new GridLayoutManager(MainActivity.this, 2);
                }
                recyclerView.setLayoutManager(layoutManager);
            }
        });
        // specify an adapter (see also next example)
        myDataAdapter = new MyDataAdapter(this);
        recyclerView.setAdapter(myDataAdapter);

        selectionTracker = new SelectionTracker.Builder<String>(
                "mySelection",
                recyclerView,
                new GameItemKeyProvider(ItemKeyProvider.SCOPE_CACHED, myDataAdapter),
                new GameDetailsLookup(recyclerView),
                StorageStrategy.createStringStorage())
                .withSelectionPredicate(
                        SelectionPredicates.<String>createSelectAnything())
                .build();

        myDataAdapter.setSelectionTracker(selectionTracker);


        selectionTracker.addObserver(new SelectionTracker.SelectionObserver() {
            @Override
            public void onItemStateChanged(@NonNull Object key, boolean selected) {
                super.onItemStateChanged(key, selected);
                if (!selectionTracker.getSelection().isEmpty()) {
                    if (actionMode == null) {
                        toolbar.startActionMode(MainActivity.this);
                    }
                    updateActionMode();
                } else {
                    if (actionMode != null) {
                        actionMode.finish();
                        actionMode = null;
                    }
                }
            }
        });
        myDataAdapter.bindViewModels(DataGenerator.generateData());
    }

    private void updateActionMode() {
        if (selectionTracker.getSelection().size() > 1) {
            actionMode.setTitle(Integer.toString(selectionTracker.getSelection().size()));
            actionMode.setSubtitle("jeux selectionnés");
        } else {
            actionMode.setTitle("1");
            actionMode.setSubtitle("jeu selectionné");
        }
    }

    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onGameInfoClicked(String gameTitle) {
        displaySnackBar(getString(R.string.game_info_clicked, gameTitle));
    }

    @Override
    public void onGameClicked(String gameTitle) {
        displaySnackBar(getString(R.string.game_clicked, gameTitle));
    }

    // Called when the action mode is created; startActionMode() was called
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // Inflate a menu resource providing context menu items
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.action_mode_menu, menu);
        this.actionMode = mode;
        return true;
    }

    // Called each time the action mode is shown. Always called after onCreateActionMode, but
    // may be called multiple times if the mode is invalidated.
    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    // Called when the user selects a contextual menu item
    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        if (menuItem.getOrder() == 0) {
            //Handle click on What's hot menu item
            displaySnackBar(getString(R.string.whatshot_clicked));
        }
        return true;
    }

    // Called when the user exits the action mode
    @Override
    public void onDestroyActionMode(ActionMode actionMode) {
        selectionTracker.clearSelection();
        this.actionMode = null;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        selectionTracker.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        selectionTracker.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


}
