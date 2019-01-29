//package com.dpdgroup.versions.android.androidversions.application;
//
//import android.app.Activity;
//import android.app.Application;
//
//import javax.inject.Inject;
//
//import dagger.android.DispatchingAndroidInjector;
//import dagger.android.HasActivityInjector;
//
//public class VersionApplication extends Application implements HasActivityInjector {
//
//    @Inject
//    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        VersionApplicationComponent.create().inject(this);
//    }
//
//    @Override
//    public DispatchingAndroidInjector<Activity> activityInjector() {
//        return dispatchingAndroidInjector;
//    }
//}
