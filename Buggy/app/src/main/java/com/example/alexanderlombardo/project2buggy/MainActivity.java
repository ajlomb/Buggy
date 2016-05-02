package com.example.alexanderlombardo.project2buggy;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexanderlombardo.project2buggy.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Turn back on once database is set up
        DBAssetHelper dbSetup = new DBAssetHelper(MainActivity.this);
        dbSetup.getReadableDatabase();

        ListView mainList = (ListView)findViewById(R.id.main_list_view);
        ListView dailyBugDisplay = (ListView)findViewById(R.id.main_daily_bug);

        //  Turn back on once database is set up
        Cursor mainCursor = new BugSQLiteOpenHelper(this).getStaticBugList();

        //  change the "null" below to a 'cursor' variable defined above(?)
        CursorAdapter dailyBugCursorAdapter = new CursorAdapter(MainActivity.this, null, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.daily_bug_format, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                ImageView dailyImageView = (ImageView)view.findViewById(R.id.daily_bug_image);
                TextView dailyCommonName = (TextView)view.findViewById(R.id.daily_bug_common_name);
                TextView dailyLatinName = (TextView)view.findViewById(R.id.daily_bug_latin_name);
                TextView dailyTruncated = (TextView)view.findViewById(R.id.daily_bug_description);

                dailyImageView.setImageResource(R.drawable.bee);
                dailyCommonName.setText("Honey Bee");
                dailyLatinName.setText("Apis malifurra");
                dailyTruncated.setText("Bees fly around and make the honey, all while holding their Queen-mother hostage!");
            }
        };
        dailyBugDisplay.setAdapter(dailyBugCursorAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
