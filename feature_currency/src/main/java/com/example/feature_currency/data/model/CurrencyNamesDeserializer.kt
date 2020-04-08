package com.example.feature_currency.data.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CurrencyNamesDeserializer : JsonDeserializer<CurrenciesDto> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrenciesDto? {

        val currencyNamesJsonObject = json.asJsonObject
        val currencyName = mutableListOf<CurrencyNameDto>()

        if (currencyNamesJsonObject != null) {
            for (key in currencyNamesJsonObject.keySet()) {
                currencyName.add(
                    CurrencyNameDto(
                        code = key,
                        name = currencyNamesJsonObject.get(key).asString
                    )
                )
            }
        }

        return CurrenciesDto(currencyName)
    }
}