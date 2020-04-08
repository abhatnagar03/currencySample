package com.example.foundation.domain

class GenericError constructor(
    val type: Type,
    cause: Throwable? = null,
    message: String = type.name
) : Throwable(message, cause) {

    enum class Type {
        OFFLINE_OR_TIMEOUT,
        CLIENT_ERROR,
        SERVER_ERROR,
        MAINTENANCE_ERROR;

        companion object {
            val DEFAULT = CLIENT_ERROR
        }
    }
}