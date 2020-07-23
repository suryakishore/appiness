package com.example.appiness

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("services/rest/")
    fun getSearchResults(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("text") text: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Observable<Response<ResponseBody>>
}