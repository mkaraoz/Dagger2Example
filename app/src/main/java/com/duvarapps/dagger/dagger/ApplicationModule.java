package com.duvarapps.dagger.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.duvarapps.dagger.api.BitfinexService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.duvarapps.dagger.AppConstants.BASE_URL;

@Module
public class ApplicationModule
{
    private final Context context;

    public ApplicationModule(Application app) {
        context = app;
    }

    @Provides // scope is not necessary for parameters stored within the module
    @Named("app")
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    @Provides
    @Named("gson")
    @Singleton
    public Retrofit provideRetrofitGson(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create()).baseUrl(BASE_URL).client(okHttpClient).build();
        return retrofit;
    }

    @Provides
    @Named("scalar")
    @Singleton
    public Retrofit provideRetrofitXml(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(
                ScalarsConverterFactory.create()).baseUrl(BASE_URL).client(okHttpClient).build();
        return retrofit;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    BitfinexService providesBitfinexService(@Named("gson") Retrofit retrofit) {
        return retrofit.create(BitfinexService.class);
    }
}
