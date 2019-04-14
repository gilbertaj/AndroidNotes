package gilbertaj.notes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteListAdapter noteListAdapter;
    private ArrayList<String> notesItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        ListView noteListView = findViewById(R.id.noteListView);

        //Setup the toolbar and fab
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateList();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        //Create the adapter from the notes list and then assign it to the ListView
        notesItemsList = getNoteNameList();
        noteListAdapter = new NoteListAdapter(getApplicationContext(), notesItemsList);
        noteListView.setAdapter(noteListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Get the current notes and add them to the List View through it's adapter
     */
    private void UpdateList() {
        //Get the current notes
        notesItemsList.add("List " + (notesItemsList.size() + 1));

        //Refresh the ListView
        noteListAdapter.notifyDataSetChanged();
    }

    /**
     * Get a list of the current notes' names
     * @return ArrayList - "ArrayList of the current notes's names"
     */
    private ArrayList<String> getNoteNameList() {
        //dummy data for now. To be implemented using sqlLite in the future
        ArrayList<String> itemsList = new ArrayList<>();
        itemsList.add("First List");
        itemsList.add("Second List");

        return itemsList;
    }
}
