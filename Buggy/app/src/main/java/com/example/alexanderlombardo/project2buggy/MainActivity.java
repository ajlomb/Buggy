package com.example.alexanderlombardo.project2buggy;

import android.content.Context;
import android.database.Cursor;
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
        BugSQLiteOpenHelper dbSetup = new BugSQLiteOpenHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        //sets listview, allows population of listview wtih data
        ListView mainList = (ListView)findViewById(R.id.main_list_view);

        //Cursor to hold database information, don't understand anything else about it or what's on the right of the "="-sign.
        final Cursor mainCursor = new BugSQLiteOpenHelper(this).getBugs();

        //CursorAdapter; this is also important for reasons I don't understand short of it allowing me to do what I'm supposed to accomplish.
        CursorAdapter searchableCursorAdapter = new CursorAdapter(MainActivity.this, null, 0) {
            //newView; gives the activity listview a custom format based on XML in layout which is linked.
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.main_list_format, parent, false);
            }
            //gives the above newView a way to populate the fields which are included in it
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                //provides variables to hold information to populate the specified fields
                TextView commonNameTV = (TextView)view.findViewById(R.id.main_list_common);
                TextView latinNameTV = (TextView)view.findViewById(R.id.main_list_latin);
                //tells the CursorAdapter which data elements of the table to put where based on the variables defined above.
                commonNameTV.setText(mainCursor.getString(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_COMMON_NAME)));
                latinNameTV.setText(mainCursor.getString(mainCursor.getColumnIndex(BugSQLiteOpenHelper.COL_LATIN_NAME)));
            }
        };
        //this does what?
        mainList.setAdapter(searchableCursorAdapter);


    }

    //related to search function, no idea
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
