package com.example.alexanderlombardo.project2buggy;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class ApplicationTest extends ApplicationTestCase<Application> {
    private Context context;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        context = getSystemContext();
    }

    @SmallTest
    public void test() {
        int size = BugSQLiteOpenHelper.getInstance(context).getBugs().getCount();
        assertEquals(8, size);
        assertNotNull(BugSQLiteOpenHelper.getInstance(context));
    }
}