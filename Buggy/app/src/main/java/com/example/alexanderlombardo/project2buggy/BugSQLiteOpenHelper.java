package com.example.alexanderlombardo.project2buggy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexanderlombardo on 4/29/16.
 */
public class BugSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BUG_TABLE";
    public static final String BUG_TABLE_TITLE = "BUG_TABLE";

    public static final String COL_ID = "_id";
    public static final String COL_COMMON_NAME = "COMMON_NAME";
    public static final String COL_LATIN_NAME = "LATIN_NAME";
    public static final String COL_NUM_LEGS = "NUM_LEGS";
    public static final String COL_WINGS = "WINGS";
    public static final String COL_COLOR = "COLOR";
    public static final String COL_DESCRIPTION = "DESCRIPT";

    public static final String[] BUG_COLUMNS = {COL_ID, COL_COMMON_NAME, COL_LATIN_NAME, COL_NUM_LEGS, COL_WINGS, COL_COLOR, COL_DESCRIPTION};

    private static BugSQLiteOpenHelper bugInstance;

    private static final String CREATE_BUG_TABLE =
            "CREATE TABLE " + BUG_TABLE_TITLE +
                    "(" + COL_ID + " INTEGER PRIMARY KEY, " +
                    COL_COMMON_NAME + " TEXT, " +
                    COL_LATIN_NAME + " TEXT, " +
                    COL_NUM_LEGS + " TEXT, " +
                    COL_WINGS + " TEXT, " +
                    COL_COLOR + " TEXT, " +
                    COL_DESCRIPTION + " TEXT);";

    public static final String DROP_BUG_TABLE = "DROP TABLE IF EXISTS " + CREATE_BUG_TABLE;

    //No understanding of what this does
    public BugSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static BugSQLiteOpenHelper getInstace(Context context) {
        if(bugInstance == null) {
            bugInstance = new BugSQLiteOpenHelper(context.getApplicationContext());
        }
        return bugInstance;
    }

    //Don't know what this is doing short of executing the create table SQL tied to the variable.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BUG_TABLE);


    }

    //Supposedly upgrades the database if there are changes.
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_BUG_TABLE);
        onCreate(db);
    }

    //Allows adding of data to DB.
    public void insertBugData(int id, String commonName, String latinName, String numLegs, String withWings, String bugColor, String bugDescript){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("_id", id);
        values.put("COMMON_NAME", commonName);
        values.put("LATIN_NAME", latinName);
        values.put("NUM_LEGS", numLegs);
        values.put("WINGS", withWings);
        values.put("COLOR", bugColor);
        values.put("DESCRIPT", bugDescript);

        db.insert("BUG_TABLE", null, values);
    }



    //called on in the MainActivity, accesses database and return information from it?
    public Cursor getBugs() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor bugsCursor = db.query(BUG_TABLE_TITLE, BUG_COLUMNS, null, null, null, null, null, null);
        return bugsCursor;
    }

}
