package com.example.balldontlie.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusResponse(
    val get: String,
//    val parameters: List<Any?>,
//    val errors: List<Any?>,
    val results: Int,
    val response: Response,
) {
    @Serializable
    data class Response(
        val account: Account,
        val subscription: Subscription,
        val requests: Requests,
    ) {
        @Serializable
        data class Account(
            val firstname: String,
            val lastname: String,
            val email: String,
        )

        @Serializable
        data class Subscription(
            val plan: String,
            val end: String,
            val active: Boolean,
        )

        @Serializable
        data class Requests(
            val current: Int,
            @SerialName("limit_day")
            val limitDay: Int,
        )
    }
}


