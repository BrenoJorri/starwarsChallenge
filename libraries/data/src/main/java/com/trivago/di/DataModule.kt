package com.trivago.di

import android.content.Context
import androidx.room.Room
import com.trivago.CharacterDetailRepositoryImpl
import com.trivago.FavoritesRepositoryImpl
import com.trivago.SearchRepositoryImpl
import com.trivago.core.BuildConfig
import com.trivago.domain.detail.repository.CharacterDetailRepository
import com.trivago.domain.favorites.repository.FavoritesRepository
import com.trivago.domain.search.repository.SearchRepository
import com.trivago.local.FavoriteDatabase
import com.trivago.mapper.detail.CharacterFilmMapper
import com.trivago.mapper.detail.CharacterHomeWorldMapper
import com.trivago.mapper.detail.CharacterSpeciesMapper
import com.trivago.mapper.favorite.FavoritesCharacterMapper
import com.trivago.mapper.search.SearchMapper
import com.trivago.remote.CharacterDetailApi
import com.trivago.remote.SearchApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single { provideOkhttp() }
    single { provideRetrofit(get()) }
    single { provideSearchApi(get()) }
    single { provideDetailApi(get()) }
    single { SearchMapper() }
    single { CharacterFilmMapper() }
    single { CharacterSpeciesMapper() }
    single { CharacterHomeWorldMapper() }
    single { FavoritesCharacterMapper() }
    single { provideCharacterDao(androidContext()).getCharacterDao() }
    single<CharacterDetailRepository> { CharacterDetailRepositoryImpl(get(), get(), get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }
    single<FavoritesRepository> { FavoritesRepositoryImpl(get(), get()) }
}

private fun provideCharacterDao(context: Context): FavoriteDatabase {
    return Room.databaseBuilder(context, FavoriteDatabase::class.java, "star-wars-db")
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideSearchApi(retrofit: Retrofit) = retrofit.create(SearchApi::class.java)
private fun provideDetailApi(retrofit: Retrofit) = retrofit.create(CharacterDetailApi::class.java)

private fun provideRetrofit(okHttpClient: OkHttpClient) =
    Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://swapi.dev/api/")
        .build()

private fun provideOkhttp() =
    OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
