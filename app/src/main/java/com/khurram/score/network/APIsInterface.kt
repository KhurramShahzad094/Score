package com.khurram.score.network

import com.google.gson.JsonElement
import com.khurram.score.model.ScoreResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIsInterface {

    @Headers("Accept: application/json")
    @GET("endpoint.json")
    suspend fun getScore(): JsonElement


}