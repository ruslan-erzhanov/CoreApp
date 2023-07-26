package com.erzhanov.coreapp.core.network

object ApiContract {
    object Auth {
        const val SIGN_UP = "sign-up"
        const val SIGN_IN = "sign-in"
    }

    object Reddit {
        const val TOP = "top.json"
    }

    object Headers {
        const val AUTHORIZATION = "Authorization"
        const val BEARER = "Bearer:"
        const val API_KEY = "x-api-key"
        const val CLIENT_VERSION = "client-version"
        const val PLATFORM = "platform"
        const val UUID = "uuid"
        const val DEBUG = "debug"
        const val VERSION = "version"
    }
    object Params {
        const val AFTER = "after"
        const val LIMIT = "limit"
    }
}