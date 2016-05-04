package com.example.alexanderlombardo.project2buggy;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates an instance of the SQLiteOpenHelper helper, uses OpenHelper to get readable database.
        BugSQLiteOpenHelper dbSetup = BugSQLiteOpenHelper.getInstance(MainActivity.this);
        dbSetup.getReadableDatabase();

        //Instantiates the MainActivity ListView allowing it to be called, customized, and populated.
        ListView mainList = (ListView)findViewById(R.id.main_list_view);

        //Creates a Cursor populated by getBugs Method in BugSQLiteOpenHelper Class.
        final Cursor mainCursor = new BugSQLiteOpenHelper(this).getBugs();

        //Custom CursorAdapter which uses data held by mainCursor to populate custom ListView layouts.
        CursorAdapter bugCursorAdapter = new CursorAdapter(MainActivity.this, mainCursor, 0) {
            //LayoutInflater links a custom XML layout to the parent XML layout of MainActivity.
            @Override
            public View newView(Context context, Cursor mainCursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.main_list_format, parent, false);
            }
            @Override
            public void bindView(View view, Context context, Cursor mainCursor) {
                //Provides variables to hold information to populate the identified fields in the main_list_format layout.
                TextView commonNameTV = (TextView)view.findViewById(R.id.main_list_common);
                TextView latinNameTV = (TextView)view.findViewById(R.id.main_list_latin);
                //Populates TextViews using data in the specified columns of the database held in mainCursor.
                commonNameTV.setText(mainCursor.getString(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_COMMON_NAME)));
                latinNameTV.setText(mainCursor.getString(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_LATIN_NAME)));
            }
        };
        //Setting butCursorAdapter (instantiated above) to the ListView in the MainActivity called mainList;
        //  allows list to be populated by data passed through the custom CursorAdapter.
        mainList.setAdapter(bugCursorAdapter);
        //Seeds the database using the insertBugData Method in the BugSQLiteOpenHelper Class.
        dbSetup.insertBugData(1, "Honey Bee",
                "Apis mellifera", "6", "1", "black, yellow",
                "99% of bees you've seen are hard working ladies.");
        dbSetup.insertBugData(2, "Praying Mantis",
                "Stagmomantis californica", "6", "1", "yellow, green, brown",
                "Also a style of kung-fu");
        dbSetup.insertBugData(3, "Black Widow",
                "Latrodectus hesperus", "8", "0", "black, red",
                "The western black widow spider or western widow, is a venomous spider species found in western regions of North America.");
        dbSetup.insertBugData(4, "House Cricket",
                "Acheta domestica", "6", "1", "brown, grey",
                "The house cricket is typically gray or brownish in color, growing to 16–21 millimetres (0.63–0.83 in) in length.");
        dbSetup.insertBugData(5, "Tiger Swallowtail",
                "Papilio glaucus", "6", "1", "yellow, black",
                "a species of swallowtail butterfly native to eastern North America");
        dbSetup.insertBugData(6, "Earthworm",
                "Lumbricus terrestris", "0", "0", "brown, pink",
                "An earthworm is a tube-shaped, segmented worm found in the phylum Annelida. Earthworms are commonly found living in soil, feeding on live and dead organic matter.");
        dbSetup.insertBugData(7, "Black Bean Aphid",
                "Aphis fabae", "6", "1", "black",
                "Other common names include blackfly, bean aphid and beet leaf aphid. In the warmer months of the year it is found in large numbers on the undersides of leaves and on the growing tips of host plants, including various agricultural crops and many wild and ornamental plants.");
        dbSetup.insertBugData(8, "Green Aphid",
                "Acyrthosiphon pisum", "6", "1", "green, yellow",
                "Acyrthosiphon pisum, commonly known as the pea aphid (and colloquially known as the green dolphin, pea louse, and clover louse), is a sap-sucking insect in the Aphididae family.");

        //Dumps a log of the current data held in the mainCursor to the log, does nothing to affect the app while running.
        DatabaseUtils.dumpCursor(mainCursor);

        //Sets the OnItemClickListener to entries in the ListView to move to the DetailsActivity Class;
        //mainCursor is set to the index position of the entry clicked with moveToPosition;
        //Passes the ColumnIndex of the entry clicked on, with key "id", to the DetailsActivity Class;
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);
                mainCursor.moveToPosition(position);
                detailsIntent.putExtra("id", mainCursor.getInt(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_ID)));
                startActivity(detailsIntent);
            }
        });
    }

//    related to search function
//    @Override
//    public boolean onCreateOptionsMenu (Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_search, menu);
//
//        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//
//        return true;
//    }
}
