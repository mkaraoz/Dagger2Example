package com.duvarapps.dagger;

import android.app.Application;
import android.content.Context;

import com.duvarapps.dagger.dagger.ApplicationComponent;
import com.duvarapps.dagger.dagger.ApplicationModule;
import com.duvarapps.dagger.dagger.DaggerApplicationComponent;

public class MyApp extends Application
{
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
                new ApplicationModule(this)).build();
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}


