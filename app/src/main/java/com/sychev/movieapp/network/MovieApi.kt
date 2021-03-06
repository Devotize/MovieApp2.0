package com.sychev.movieapp.network

import com.sychev.movieapp.network.model.MovieDto
import com.sychev.movieapp.network.model.PersonDto
import com.sychev.movieapp.network.responses.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): MovieSearchResponse

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("query") query: String,
        @Query("page") page: Int
    ): TvShowSearchResponse

    @GET("search/multi")
    suspend fun searchMulti(
        @Query("query") query: String,
        @Query("page") page: Int
    ): MultiSearchResponse

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id", encoded = true) id: Int): MovieDto

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id", encoded = true) id: Int): CreditsResponse

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendations(@Path("movie_id", encoded = true) id: Int): MovieSearchResponse

    @GET("person/{person_id}")
    suspend fun getPerson(@Path("person_id", encoded = true) id: Int): PersonDto?

    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMovieCredits(@Path("person_id", encoded = true) id: Int): PersonMovieCreditsResponse
}