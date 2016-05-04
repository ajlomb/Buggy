package com.example.alexanderlombardo.project2buggy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public static BugSQLiteOpenHelper getInstance(Context context) {
        if(bugInstance == null) {
            bugInstance = new BugSQLiteOpenHelper(context.getApplicationContext());
        }
        return bugInstance;
    }

    //Don't know what this is doing short of executing the create table SQL tied to the variable.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BUG_TABLE);

        db.close();

        insertBugData(1, "Honey Bee",
                "Apis mellifera", "6", "1", "black, yellow",
                "99% of bees you've seen are hard working ladies.");
        insertBugData(2, "Praying Mantis",
                "Stagmomantis californica", "6", "1", "yellow, green, brown",
                "Also a style of kung-fu");
        insertBugData(3, "Black Widow",
                "Latrodectus hesperus", "8", "0", "black, red",
                "The western black widow spider or western widow, is a venomous spider species found in western regions of North America.");
        insertBugData(4, "House Cricket",
                "Acheta domestica", "6", "1", "brown, grey",
                "The house cricket is typically gray or brownish in color, growing to 16–21 millimetres (0.63–0.83 in) in length.");
        insertBugData(5, "Tiger Swallowtail",
        "Papilio glaucus", "6", "1", "yellow, black",
        "a species of swallowtail butterfly native to eastern North America");
        insertBugData(6, "Earthworm",
        "Lumbricus terrestris", "0", "0", "brown, pink",
        "An earthworm is a tube-shaped, segmented worm found in the phylum Annelida. Earthworms are commonly found living in soil, feeding on live and dead organic matter.");
        insertBugData(7, "Black Bean Aphid",
        "Aphis fabae", "6", "1", "black",
        "Other common names include blackfly, bean aphid and beet leaf aphid. In the warmer months of the year it is found in large numbers on the undersides of leaves and on the growing tips of host plants, including various agricultural crops and many wild and ornamental plants.");
        insertBugData(8, "Green Aphid",
                "Acyrthosiphon pisum", "6", "1", "green, yellow",
                "Acyrthosiphon pisum, commonly known as the pea aphid (and colloquially known as the green dolphin, pea louse, and clover louse), is a sap-sucking insect in the Aphididae family.");


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
        db.close();
    }

    //Called on in MainActivity
    public Cursor getBugs() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor bugsCursor = db.query(BUG_TABLE_TITLE, BUG_COLUMNS, null, null, null, null, null, null);
        return bugsCursor;
    }

    //
    public Cursor getBugDetails(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor insectCursor = db.query(BUG_TABLE_TITLE,
                new String[]{COL_COMMON_NAME, COL_LATIN_NAME, COL_NUM_LEGS, COL_WINGS, COL_COLOR, COL_DESCRIPTION},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if(insectCursor.moveToFirst()){
            return insectCursor;
        }else{
            return null;
        }
    }

    public Cursor searchBugDatabase(String query){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor searchCursor = db.query(BUG_TABLE_TITLE,
                BUG_COLUMNS,
                COL_NUM_LEGS + " LIKE ? OR " + COL_COLOR + " LIKE ? ",
                new String[]{"%"+query+"%", "%"+query+"%"},
                null, null, null, null);

        return searchCursor;
    }

}
