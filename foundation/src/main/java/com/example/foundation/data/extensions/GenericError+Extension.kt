package com.example.foundation.data.extensions

import com.example.foundation.domain.GenericError

fun Int.fromHttpErrorCodeToGenericErrorType() = when (this) {
   in 400..499 -> GenericError.Type.CLIENT_ERROR
   503 -> GenericError.Type.MAINTENANCE_ERROR
   in 500..599 -> GenericError.Type.SERVER_ERROR
   else -> GenericError.Type.DEFAULT
}