package com.duvarapps.dagger.dagger;

import com.duvarapps.dagger.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent
{
    void inject(MainActivity activity);
}
