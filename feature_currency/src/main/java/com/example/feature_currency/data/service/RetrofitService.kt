package com.example.feature_currency.data.service

import com.example.feature_currency.data.model.CurrenciesDto
import com.example.feature_currency.data.model.RateDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("./latest")
    fun getRates(
        @Query("base") base: String
    ): Observable<RateDto>

    @GET("https://openexchangerates.org/api/currencies.json")
    fun getCurrencies(): Observable<CurrenciesDto>
}