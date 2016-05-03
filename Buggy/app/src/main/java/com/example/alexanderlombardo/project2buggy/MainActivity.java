package com.example.alexanderlombardo.project2buggy;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //oncreate, sets XML page to display on
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //makes SQLiteOpenHelper helper, gets readable database, no idea beyond that.
        BugSQLiteOpenHelper dbSetup = BugSQLiteOpenHelper.getInstance(MainActivity.this);
        dbSetup.getReadableDatabase();

        //sets listview, allows population of listview wtih data
        ListView mainList = (ListView)findViewById(R.id.main_list_view);

    //    BugSQLiteOpenHelper bugHelper = BugSQLiteOpenHelper.getInstance(MainActivity.this);

        //Cursor to hold database information
        final Cursor mainCursor = new BugSQLiteOpenHelper(this).getBugs();

        //CursorAdapter; this is also important for reasons I don't understand short of it allowing me to do what I'm supposed to accomplish.
        CursorAdapter bugCursorAdapter = new CursorAdapter(MainActivity.this, mainCursor, 0) {
            //newView; gives the activity listview a custom format based on XML in layout which is linked.
            @Override
            public View newView(Context context, Cursor mainCursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.main_list_format, parent, false);
            }
            //gives the above newView a way to populate the fields which are included in it
            @Override
            public void bindView(View view, Context context, Cursor mainCursor) {
                //provides variables to hold information to populate the specified fields
                TextView commonNameTV = (TextView)view.findViewById(R.id.main_list_common);
                TextView latinNameTV = (TextView)view.findViewById(R.id.main_list_latin);
                //tells the CursorAdapter which data elements of the table to put where based on the variables defined above.
                commonNameTV.setText(mainCursor.getString(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_COMMON_NAME)));
                latinNameTV.setText(mainCursor.getString(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_LATIN_NAME)));
            }
        };
        //this does what?
        mainList.setAdapter(bugCursorAdapter);
        //adding data to the table
        dbSetup.insertBugData(1, "Honey Bee", "Apis mellifera", "6", "1", "black, yellow", "99% of bees you've seen are hard working ladies.");
        dbSetup.insertBugData(2, "Praying Mantis", "Stagmomantis californica", "6", "1", "yellow, green, brown", "Also a style of kung-fu");
        dbSetup.insertBugData(3, "Black Widow", "Latrodectus hesperus", "8", "0", "black, red", "The western black widow spider or western widow, is a venomous spider species found in western regions of North America.");
        dbSetup.insertBugData(4, "House Cricket", "Acheta domestica", "6", "1", "brown, grey", "The house cricket is typically gray or brownish in color, growing to 16–21 millimetres (0.63–0.83 in) in length.");
        dbSetup.insertBugData(5, "Tiger Swallowtail", "Papilio glaucus", "6", "1", "yellow, black", "a species of swallowtail butterfly native to eastern North America");
        dbSetup.insertBugData(6, "Earthworm", "Lumbricus terrestris", "0", "0", "brown, pink", "An earthworm is a tube-shaped, segmented worm found in the phylum Annelida. Earthworms are commonly found living in soil, feeding on live and dead organic matter.");
        dbSetup.insertBugData(7, "Black Bean Aphid", "Aphis fabae", "6", "1", "black", "Other common names include blackfly, bean aphid and beet leaf aphid. In the warmer months of the year it is found in large numbers on the undersides of leaves and on the growing tips of host plants, including various agricultural crops and many wild and ornamental plants.");
        dbSetup.insertBugData(8, "Green Aphid", "Acyrthosiphon pisum", "6", "1", "green, yellow", "Acyrthosiphon pisum, commonly known as the pea aphid (and colloquially known as the green dolphin, pea louse, and clover louse), is a sap-sucking insect in the Aphididae family.");

        DatabaseUtils.dumpCursor(mainCursor);
    }

//    related to search function, no idea
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
