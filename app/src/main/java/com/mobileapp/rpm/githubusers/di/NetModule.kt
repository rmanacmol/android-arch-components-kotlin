package com.mobileapp.rpm.githubusers.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mobileapp.rpm.githubusers.BuildConfig
import com.mobileapp.rpm.githubusers.R
import com.mobileapp.rpm.githubusers.data.remote.RemoteService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule(private val mApplication: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = mApplication

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()
    //return GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            // httpClient.addInterceptor(ChuckInterceptor(mApplication))
        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(mApplication.getString(R.string.base_service_url))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): RemoteService = retrofit.create(RemoteService::class.java)

}