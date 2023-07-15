package com.erzhanov.coreapp.core.network

object ApiContract {
    object Auth {
        const val SIGN_UP = "sign-up"
    }

    object User {
        const val USER = "user"
        const val ACTIVITIES = "$USER/recent-activity"
        const val ACTIVITY_PAGE = "page"
        const val ACTIVITY_PAGE_SIZE = "per_page"
    }
}