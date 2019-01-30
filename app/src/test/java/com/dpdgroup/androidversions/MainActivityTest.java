package com.dpdgroup.androidversions;

import com.dpdgroup.androidversions.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric
                .buildActivity(MainActivity.class)
                .create()
                .get();
    }

    @After
    public void tearDown() {
        mainActivity = null;
    }

    @Test
    public void testActivityIsNotNull() {
        assertNotNull(mainActivity);
    }

}
