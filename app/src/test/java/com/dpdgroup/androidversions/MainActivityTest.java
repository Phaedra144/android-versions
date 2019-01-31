package com.dpdgroup.androidversions;



import com.dpdgroup.androidversions.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;
    private ActivityController<MainActivity> activityController;
    private ShadowActivity mainActivityShadow;

    @Before
    public void setUp() {
        mainActivity = Robolectric
                .buildActivity(MainActivity.class)
                .create()
                .get();
//        activityController = Robolectric
//                .buildActivity(MainActivity.class)
//                .create()
//                .start();
//        mainActivityShadow = shadowOf(activityController.get());

    }

    @After
    public void tearDown() {
        mainActivity = null;
    }

    @Test
    public void testActivityIsNotNull() {
        assertNotNull(mainActivity);
    }

    @Test
    public void testCodeName() {
        assertNotNull(mainActivity.findViewById(R.id.codeNameText));
    }

}
