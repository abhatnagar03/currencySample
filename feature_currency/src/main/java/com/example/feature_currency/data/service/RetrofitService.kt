package com.example.feature_currency.data.service

import com.example.feature_currency.data.model.RateDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("./latest")
    fun getRates(
        @Query("base") base: String
    ): Single<RateDto>
}