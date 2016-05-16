package com.example.alexanderlombardo.project2buggy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    Cursor masterCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        //Instantiates OpenHelper Class for use in this Activity.
        BugSQLiteOpenHelper detailsHelper = BugSQLiteOpenHelper.getInstance(DetailsActivity.this);

        masterCursor = new BugSQLiteOpenHelper(this).getBugs();

        int id = getIntent().getIntExtra("id", -1);

        if(id>=0){
            Cursor detailsCursor = detailsHelper.getBugDetails(id);

            int captureId = detailsCursor.getInt(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_ID));
            String captureCommonName = detailsCursor.getString(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_COMMON_NAME));
            String captureLatinName = detailsCursor.getString(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_LATIN_NAME));
            String captureNumLegs = detailsCursor.getString(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_NUM_LEGS));
            String captureWings = detailsCursor.getString(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_WINGS));
            String captureColors = detailsCursor.getString(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_COLOR));
            String captureDescript = detailsCursor.getString(detailsCursor.getColumnIndex(BugSQLiteOpenHelper.COL_DESCRIPTION));

            ImageView bugImage = (ImageView)findViewById(R.id.details_image_view);
            TextView commonName = (TextView)findViewById(R.id.details_common_name);
            TextView latinName = (TextView)findViewById(R.id.details_latin_name);
            TextView numLegs = (TextView)findViewById(R.id.details_num_legs);
            TextView detailWings = (TextView)findViewById(R.id.details_wings);
            TextView detailColors = (TextView)findViewById(R.id.details_color);
            TextView detailDescription = (TextView)findViewById(R.id.details_details_text);

            //With the XML "scrollbar" attribute, allow scrolling of married TextView when there is more text than the size of the view allows
            detailDescription.setMovementMethod(new ScrollingMovementMethod());

            if(captureWings.equals("1")){
                detailWings.setText("This bug has wings!");
            } else {
                detailWings.setText("No wings.");
            }
            detailColors.setText("Colors: "+captureColors);
            numLegs.setText("Legs: "+captureNumLegs);
            commonName.setText("Common Name: \n" +captureCommonName);
            latinName.setText("Binomial Name: \n" +captureLatinName);
            detailDescription.setText(captureDescript);

            switch(captureId) {
                case 1:     bugImage.setImageResource(R.drawable.bee);
                    break;
                case 2:     bugImage.setImageResource(R.drawable.mantis);
                    break;
                case 3:     bugImage.setImageResource(R.drawable.blackwidow);
                    break;
                case 4:     bugImage.setImageResource(R.drawable.cricket);
                    break;
                case 5:     bugImage.setImageResource(R.drawable.tigerswallowtail);
                    break;
                case 6:     bugImage.setImageResource(R.drawable.earthworm);
                    break;
                case 7:     bugImage.setImageResource(R.drawable.blackaphid);
                    break;
                case 8:     bugImage.setImageResource(R.drawable.greenaphid);
                    break;
                case 9:     bugImage.setImageResource(R.drawable.ladybeetle);
                    break;
                case 10:    bugImage.setImageResource(R.drawable.woodlouse);
                    break;
                case 11:    bugImage.setImageResource(R.drawable.pillipede);
                    break;
            }
            bugImage.setTransitionName("moving_bug");
        }
    }
}
