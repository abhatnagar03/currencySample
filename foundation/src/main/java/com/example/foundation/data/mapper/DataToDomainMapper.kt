package com.example.foundation.data.mapper

public interface DataToDomainMapper<in Data, out Domain> {

   fun transform(dataModel: Data): Domain
}