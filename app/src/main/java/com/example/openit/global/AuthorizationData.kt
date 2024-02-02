package com.example.openit.global
object AuthorizationData {
    @Volatile
    var bear_token = ""
        @Synchronized
        get() = field
        @Synchronized
        set(value) {
            field = "Bearer ${value}"
        }
}