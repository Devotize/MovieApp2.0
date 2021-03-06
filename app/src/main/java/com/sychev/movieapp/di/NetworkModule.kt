package com.sychev.movieapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.sychev.movieapp.network.MovieApi
import com.sychev.movieapp.network.mapper.MovieDtoMapper
import com.sychev.movieapp.network.mapper.MultimediaDtoMapper
import com.sychev.movieapp.network.mapper.PersonDtoMapper
import com.sychev.movieapp.network.mapper.TvShowDtoMapper
import com.sychev.movieapp.network.utils.ConnectionLiveData
import com.sychev.movieapp.util.API_KEY
import com.sychev.movieapp.util.API_LANGUAGE
import com.sychev.movieapp.util.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
    fun provideConnectivityLiveData(@ApplicationContext context: Context): ConnectionLiveData {
        return ConnectionLiveData(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient{
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

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Singleton
    @Provides
    fun provideTvShowDtoMapper(): TvShowDtoMapper {
        return TvShowDtoMapper()
    }

    @Singleton
    @Provides
    fun providePersonDtoMapper(): PersonDtoMapper {
        return PersonDtoMapper()
    }

    @Singleton
    @Provides
    fun provideMultimedaiDtoMapper(): MultimediaDtoMapper{
        return MultimediaDtoMapper()
    }

}