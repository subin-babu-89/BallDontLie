package com.example.balldontlie.network.model.games

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    val response: List<Response>,
    val results: Int,
) {
    @Serializable
    data class Response(
        val country: Country,
        val date: String,
        val id: Int,
        val league: League,
        val scores: Scores,
        val status: Status,
        val teams: Teams,
        val time: String,
        val timestamp: Int,
        val timezone: String,
        val venue: String,
    ) {

        @Serializable
        data class Country(
            val code: String,
            val flag: String,
            val id: Int,
            val name: String,
        )

        @Serializable
        data class League(
            val id: Int,
            val logo: String,
            val name: String,
            val season: String,
            val type: String,
        )

        @Serializable
        data class Scores(
            val away: Score,
            val home: Score,
        ) {
            @Serializable
            data class Score(
                @SerialName("quarter_1")
                val quarter1: Int,
                @SerialName("quarter_2")
                val quarter2: Int,
                @SerialName("quarter_3")
                val quarter3: Int,
                @SerialName("quarter_4")
                val quarter4: Int,
                val total: Int,
            )
        }

        @Serializable
        data class Status(
            val long: String,
            val short: String,
        )


        @Serializable
        data class Teams(
            val away: Team,
            val home: Team,
        ) {
            @Serializable
            data class Team(
                val id: Int,
                val logo: String,
                val name: String,
            )
        }
    }
}