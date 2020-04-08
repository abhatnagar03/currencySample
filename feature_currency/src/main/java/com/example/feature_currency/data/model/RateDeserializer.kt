package com.example.feature_currency.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RateDeserializer : JsonDeserializer<RateDto> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): RateDto? {

        val currenciesJsonObject = json.asJsonObject

        val ratesJsonObject = currenciesJsonObject?.get("rates")?.asJsonObject
        val rates = mutableListOf<CurrencyDto>()

        if (ratesJsonObject != null) {
            for (key in ratesJsonObject.keySet()) {
                val rateJsonElement = ratesJsonObject.get(key)
                rates.add(
                    CurrencyDto(
                        name = key,
                        rate = rateJsonElement.asDouble
                    )
                )
            }
        }

        return RateDto(
            base = currenciesJsonObject?.get("baseCurrency")?.asString,
            currencies = rates
        )

    }
}