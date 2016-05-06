package com.example.alexanderlombardo.project2buggy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CursorAdapter bugCursorAdapter;
    CursorAdapter bugSearchCursorAdapter;
    Cursor mainCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates an instance of the SQLiteOpenHelper helper, uses OpenHelper to get readable database.
        BugSQLiteOpenHelper dbSetup = BugSQLiteOpenHelper.getInstance(MainActivity.this);
        dbSetup.getReadableDatabase();

        //Instantiates the MainActivity ListView allowing it to be called, customized, and populated.
        ListView mainList = (ListView) findViewById(R.id.main_list_view);

        //Creates a Cursor populated by getBugs Method in BugSQLiteOpenHelper Class.
        mainCursor = new BugSQLiteOpenHelper(this).getBugs();

        //Custom CursorAdapter which uses data held by mainCursor to populate custom ListView layouts.
        bugCursorAdapter = new CursorAdapter(MainActivity.this, mainCursor, 0) {
            //LayoutInflater links a custom XML layout to the parent XML layout of MainActivity.
            @Override
            public View newView(Context context, Cursor mainCursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.main_list_format, parent, false);
            }
            @Override
            public void bindView(View view, Context context, Cursor mainCursor) {
                //Provides variables to hold information to populate the identified fields in the main_list_format layout.
                TextView commonNameTV = (TextView) view.findViewById(R.id.main_list_common);
                TextView latinNameTV = (TextView) view.findViewById(R.id.main_list_latin);
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
                "Apis mellifera", "6", "1", "black yellow",
                "The western honey bee or European honey bee (Apis mellifera) is a species of honey bee. The genus name Apis is Latin for \"bee\", and mellifera means \"honey-bearing\". It has a defined social caste system and complex communication behaviors, such as intricate dance routines to indicate food availability. It is frequently maintained by beekeepers for its honey product. This species is widely distributed and an important pollinator for agriculture, though it is currently threatened by colony collapse disorder. It is also an important organism for scientific studies on social insects, especially as it now has a fully sequenced genome.  99% of bees you've seen are hard working ladies.");
        dbSetup.insertBugData(2, "Praying Mantis",
                "Stagmomantis californica", "6", "1", "yellow green brown",
                "Adult members of this species range in size from 50–60 mm in body length. There are green, yellow, and brown varieties, with subadults and adults tending to have dark transverse bands on the top of the abdomen. The wings of both sexes are mottled or suffused with dark brown or black and the hindwings are purplish. The inner forelegs are orangish, and there are some black spots near the mandibles. In most other physical respects they closely resemble other members of their mantid order, two of which are native to the state of California (the others are the slightly smaller Stagmomantis carolina and the larger and more common Stagmomantis limbata). The oothecae and hatchlings are different than those of S. limbata.  Also a style of kung-fu");
        dbSetup.insertBugData(3, "Black Widow Spider",
                "Latrodectus hesperus", "8", "0", "black red",
                "Latrodectus hesperus, the western black widow spider or western widow, is a venomous spider species found in western regions of North America. The female's body is 14–16 mm (1/2 in) in length and is black, often with an hourglass-shaped red mark on the lower abdomen. This \"hourglass\" mark can be yellow, and on rare occasions, white. The male of the species is around half this length and generally a tan color with lighter striping on the abdomen. The population was previously described as a subspecies of Latrodectus mactans and it is closely related to the northern species Latrodectus variolus. The species, as with others of the genus, build irregular or \"messy\" webs: Unlike the spiral webs or the tunnel-shaped webs of other spiders, the strands of a Latrodectus web have no apparent organization. Female black widows have potent venom composed of neurotoxins. Fatalities usually only happen with children and the elderly, however medical treatment may be required for others as well. However, the male black widow is harmless to humans. The female's consumption of the male after courtship, a cannibalistic and suicidal behaviour observed in Latrodectus hasseltii (Australia's redback), is rare in this species. Male western widows may breed several times during their relatively short lifespans. Males are known to show preference for mating with well-fed females over starved ones, taking cues from the females' webs.");
        dbSetup.insertBugData(4, "House Cricket",
                "Acheta domestica", "6", "1", "brown grey",
                "The house cricket is typically gray or brownish in color, growing to 16–21 millimetres (0.63–0.83 in) in length. Males and females look similar, but females will have an ovipositor emerging from the rear, around 12 millimetres (0.47 in) long. The ovipositor is brown-black, and is surrounded by two appendages. On females, the cerci are also more prominent.");
        dbSetup.insertBugData(5, "Tiger Swallowtail",
                "Papilio glaucus", "6", "1", "black yellow",
                "The Eastern tiger swallowtail (Papilio glaucus) is a species of swallowtail butterfly native to eastern North America. It is one of the most familiar butterflies in the eastern United States, where it is common in many different habitats. It flies from spring to fall, during which it produces two to three broods. Adults feed on the nectar of many species of flowers, mostly from those of the Apocynaceae, Asteraceae, and Fabaceae families. P. glaucus has a wingspan measuring 7.9 to 14 cm (3.1 to 5.5 in). The male is yellow with four black \"tiger stripes\" on each fore wing. Females may be either yellow or black, making them dimorphic. The yellow morph is similar to the male, but with a conspicuous band of blue spots along the hindwing, while the dark morph is almost completely black.\n" + "\n" + "The green eggs are laid singly on plants of the Magnoliaceae and Rosaceae families. Young caterpillars are brown and white; older ones are green with two black, yellow, and blue eyespots on the thorax. The caterpillar will turn brown prior to pupating. It will reach a length of 5.5 centimetres (2.2 in). The chrysalis varies from a whitish color to dark brown. Hibernation occurs in this stage in locations with cold winter months.");
        dbSetup.insertBugData(6, "Earthworm",
                "Lumbricus terrestris", "0", "0", "brown pink",
                "An earthworm is a tube-shaped, segmented worm found in the phylum Annelida. Earthworms are commonly found living in soil, feeding on live and dead organic matter. An earthworm's digestive system runs through the length of its body. It conducts respiration through its skin. It has a double transport system composed of coelomic fluid that moves within the fluid-filled coelom and a simple, closed blood circulatory system. It has a central and a peripheral nervous system. The central nervous system consists of two ganglia above the mouth, one on either side, connected to a nerve cord running back along its length to motor neurons and sensory cells in each segment. Large numbers of chemoreceptors are concentrated near its mouth. Circumferential and longitudinal muscles on the periphery of each segment enable the worm to move. Similar sets of muscles line the gut, and their actions move the digesting food toward the worm's anus.\n" +
                "\n" + "Earthworms are hermaphrodites—each individual carries both male and female sex organs. They lack either an internal skeleton or exoskeleton, but maintain their structure with fluid-filled coelom chambers that function as a hydrostatic skeleton.\n" +
                "\n" + "\"Earthworm\" is the common name for the largest members of Oligochaeta (which is either a class or a subclass depending on the author). In classical systems, they were placed in the order Opisthopora, on the basis of the male pores opening posterior to the female pores, though the internal male segments are anterior to the female. Theoretical cladistic studies have placed them, instead, in the suborder Lumbricina of the order Haplotaxida, but this may again soon change. Folk names for the earthworm include \"dew-worm\", \"rainworm\", \"night crawler\", and \"angleworm\" (due to its use as fishing bait)."+
                "\n" +"Earthworm Jim loves plasma weapons.");
        dbSetup.insertBugData(7, "Black Bean Aphid",
                "Aphis fabae", "6", "1", "black",
                "The black bean aphid (Aphis fabae) is a small black insect in the Aphis genus, with a broad, soft body, a member of the order Hemiptera. Other common names include blackfly, bean aphid and beet leaf aphid. In the warmer months of the year it is found in large numbers on the undersides of leaves and on the growing tips of host plants, including various agricultural crops and many wild and ornamental plants. Both winged and wingless forms exist and at this time of year, they are all females. They suck sap from stems and leaves and cause distortion of the shoots, stunted plants, reduced yield and spoiled crops. This aphid also acts as a vector for viruses that cause plant disease and the honeydew it secretes may encourage the growth of sooty mould. It breeds profusely by live birth but its numbers are kept in check, especially in the later part of the summer, by various predatory and parasitic insects. Ants feed on the honeydew it produces and take active steps to remove the aphid's enemies. It is a widely distributed pest of agricultural crops and can be controlled by chemical or biological means. In the autumn, winged forms move to different host plants where both males and females are produced. These mate and the females lay eggs which overwinter.");
        dbSetup.insertBugData(8, "Pea Aphid",
                "Acyrthosiphon pisum", "6", "1", "green",
                "Acyrthosiphon pisum, commonly known as the pea aphid (and colloquially known as the green dolphin, pea louse, and clover louse), is a sap-sucking insect in the Aphididae family. It feeds on several species of legumes (plant family Fabaceae) worldwide, including forage crops, such as pea, clover, alfalfa, and broad bean, and ranks among the aphid species of major agronomical importance. The pea aphid is a model organism for biological study whose genome has been sequenced and annotated.");


        //Dumps a log of the current data held in the mainCursor to the log, does nothing to affect the app while running.
        //DatabaseUtils.dumpCursor(mainCursor);

        //Sets the OnItemClickListener to entries in the ListView to move to the DetailsActivity;
        //mainCursor is set to the index position of the entry clicked with moveToPosition;
        //Passes the ColumnIndex of the entry clicked on, with key "id", to the DetailsActivity Class;
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);

                Cursor selectedCursor = (Cursor) parent.getAdapter().getItem(position);
                detailsIntent.putExtra("id", selectedCursor.getInt(selectedCursor.getColumnIndex(BugSQLiteOpenHelper.COL_ID)));
                startActivity(detailsIntent);
            }
        });
        handleSearchIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleSearchIntent(intent);
        super.onNewIntent(intent);
    }

    //    related to search function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Cursor searchCursor = BugSQLiteOpenHelper.getInstance(MainActivity.this).searchBugDatabase(query);

            if (searchCursor.getCount() == 0){
                searchCursor = BugSQLiteOpenHelper.getInstance(MainActivity.this).getBugs();
                Toast.makeText(MainActivity.this, "No valid search results, try another trait?", Toast.LENGTH_LONG).show();
            }
            ListView mainList = (ListView)findViewById(R.id.main_list_view);
            if (bugSearchCursorAdapter == null) {
                bugSearchCursorAdapter = new CursorAdapter(MainActivity.this, searchCursor, 0) {
                    @Override
                    public View newView(Context context, Cursor searchCursor, ViewGroup parent) {
                        return LayoutInflater.from(context).inflate(R.layout.main_list_format, parent, false);
                    }

                    @Override
                    public void bindView(View view, Context context, Cursor searchCursor) {
                        //Provides variables to hold information to populate the identified fields in the main_list_format layout.
                        TextView commonNameTV = (TextView) view.findViewById(R.id.main_list_common);
                        TextView latinNameTV = (TextView) view.findViewById(R.id.main_list_latin);
                        //Populates TextViews using data in the specified columns of the database held in searchCursor.
                        commonNameTV.setText(searchCursor.getString(searchCursor.getColumnIndex(BugSQLiteOpenHelper.COL_COMMON_NAME)));
                        latinNameTV.setText(searchCursor.getString(searchCursor.getColumnIndex(BugSQLiteOpenHelper.COL_LATIN_NAME)));
                    }
                };
                mainList.setAdapter(bugSearchCursorAdapter);
            } else {
                bugSearchCursorAdapter.swapCursor(searchCursor);
            }
        }
    }
}
