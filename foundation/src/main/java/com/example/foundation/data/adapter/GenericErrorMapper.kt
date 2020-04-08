package com.example.foundation.data.adapter

import com.example.foundation.data.extensions.fromHttpErrorCodeToGenericErrorType
import com.example.foundation.data.mapper.DataToDomainMapper
import com.example.foundation.domain.GenericError
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class GenericErrorMapper :
    DataToDomainMapper<Throwable, GenericError> {
   override fun transform(dataModel: Throwable) = when (dataModel) {
      is HttpException -> mapHttpExceptionToGenericError(dataModel)
      is IOException -> GenericError(GenericError.Type.OFFLINE_OR_TIMEOUT, dataModel)
      is UnknownHostException -> GenericError(GenericError.Type.OFFLINE_OR_TIMEOUT, dataModel)
      is TimeoutException -> GenericError(GenericError.Type.OFFLINE_OR_TIMEOUT, dataModel)
      else -> GenericError(GenericError.Type.DEFAULT, dataModel)
   }

   private fun mapHttpExceptionToGenericError(httpException: HttpException) =
         GenericError(httpException.code().fromHttpErrorCodeToGenericErrorType(), httpException)
}