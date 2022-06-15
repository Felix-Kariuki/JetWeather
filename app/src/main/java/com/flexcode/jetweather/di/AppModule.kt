package com.flexcode.jetweather.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.flexcode.jetweather.data.local.LocationDatabase
import com.flexcode.jetweather.data.local.LocationsDao
import com.flexcode.jetweather.data.local.LocationsRepositoryImpl
import com.flexcode.jetweather.data.remote.ApiService
import com.flexcode.jetweather.data.repository.WeatherRepository
import com.flexcode.jetweather.domain.repository.LocationsRepository
import com.flexcode.jetweather.utils.Constants.BASE_URL
import com.flexcode.jetweather.utils.Constants.LOCATION_QUERY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesLocationsRepository(db :LocationDatabase) : LocationsRepository{
        return LocationsRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiService: ApiService,
        sharedPreferences: SharedPreferences
    ) = WeatherRepository(apiService, sharedPreferences)

    @Provides
    @Singleton
    fun provideSharedPrefs(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            LOCATION_QUERY,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideLocationsDatabase(application: Application): LocationDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            LocationDatabase::class.java,
            "location_db.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}