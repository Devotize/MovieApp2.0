package com.sychev.movieapp.di

import com.google.gson.GsonBuilder
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.model.mapper.MovieDtoMapper
import com.sychev.movieapp.presentation.MainActivity
import com.sychev.movieapp.util.API_KEY
import com.sychev.movieapp.util.API_LANGUAGE
import com.sychev.movieapp.util.API_URL
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieDtoMapper(): MovieDtoMapper{
        return MovieDtoMapper()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val newHttpUrl = chain.request().url.newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .addQueryParameter("language", API_LANGUAGE)
                        .build()
                    val newRequest = chain.request().newBuilder()
                        .url(newHttpUrl)
                        .build()
                    return chain.proceed(newRequest)
                }

            }).addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }
    @Singleton
    @Provides
    fun provideMovieApi(client: OkHttpClient): MovieApi{
        return Retrofit.Builder()
            .client(client)
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MovieApi::class.java)
    }

}