package com.example.balldontlie.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.balldontlie.network.model.games.GamesResponse

@Composable
fun GamesListItem(modifier: Modifier = Modifier, game: GamesResponse.Response) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GameLeagueLogo(icon = game.league.logo)
        ScoreWithTeamNames(
            homeTeam = game.teams.home,
            homeScore = game.scores.home.total,
            awayTeam = game.teams.away,
            awayScore = game.scores.away.total
        )
        GameLocation(location = game.venue)
    }
}

@Composable
fun GameLeagueLogo(modifier: Modifier = Modifier, icon: String) {
    AsyncImage(
        model = icon,
        contentDescription = "game-league-icon",
        modifier = modifier.size(60.dp)
    )
}

@Composable
fun GameLocation(modifier: Modifier = Modifier, location: String) {
    Text(text = location, modifier = modifier)
}

@Composable
fun TeamName(modifier: Modifier = Modifier, team: GamesResponse.Response.Teams.Team) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = team.name)
        AsyncImage(
            modifier = Modifier.size(50.dp),
            model = team.logo,
            contentDescription = "team-logo"
        )
    }
}

@Preview
@Composable
private fun TeamNamePreview() {
    val team = GamesResponse.Response.Teams.Team(
        name = "Utah Jazz",
        logo = "https://media.api-sports.io/basketball/teams/160.png",
        id = 160
    )
    TeamName(
        team = team
    )
}

@Composable
fun ScoreWithTeamNames(
    modifier: Modifier = Modifier,
    homeTeam: GamesResponse.Response.Teams.Team,
    awayTeam: GamesResponse.Response.Teams.Team,
    homeScore: Int,
    awayScore: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TeamName(team = homeTeam, modifier = Modifier.weight(1f))
        GameScores(homeScore = homeScore, awayScore = awayScore, modifier = Modifier.weight(1f))
        TeamName(team = awayTeam, modifier = Modifier.weight(1f))
    }
}


@Preview
@Composable
private fun ScoreWithTeamNamesPreview() {
    val team = GamesResponse.Response.Teams.Team(
        name = "Utah Jazz",
        logo = "https://media.api-sports.io/basketball/teams/160.png",
        id = 160
    )
    ScoreWithTeamNames(homeTeam = team, homeScore = 160, awayScore = 160, awayTeam = team)
}

@Composable
fun GameScores(modifier: Modifier = Modifier, homeScore: Int, awayScore: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
    ) {
        GameScore(score = homeScore)
        Text(text = "vs")
        GameScore(score = awayScore)
    }
}

@Composable
fun GameScore(modifier: Modifier = Modifier, score: Int) {
    Text(modifier = modifier, fontSize = 24.sp, text = score.toString())
}

@Preview
@Composable
private fun GamesScorePreview() {
    GameScores(homeScore = 117, awayScore = 109)
}

private val gamesListItem = GamesResponse.Response(
    id = 23523,
    country = GamesResponse.Response.Country(
        id = 5,
        code = "US",
        name = "USA",
        flag = "https://media.api-sports.io/flags/us.svg",
    ),
    date = "2023-12-30T14:00:00-08:00",
    league = GamesResponse.Response.League(
        id = 12,
        name = "NBA",
        type = "League",
        season = "2023-2024",
        logo = "https://media.api-sports.io/basketball/leagues/12.png"
    ),
    scores = GamesResponse.Response.Scores(
        home = GamesResponse.Response.Scores.Score(
            quarter1 = 27,
            quarter2 = 27,
            quarter3 = 34,
            quarter4 = 29,
            total = 117
        ),
        away = GamesResponse.Response.Scores.Score(
            quarter1 = 28,
            quarter2 = 25,
            quarter3 = 35,
            quarter4 = 21,
            total = 109
        )
    ),
    status = GamesResponse.Response.Status(
        long = "Game Finished",
        short = "FT"
    ),
    teams = GamesResponse.Response.Teams(
        home = GamesResponse.Response.Teams.Team(
            id = 160,
            name = "Minnesota Timberwolves",
            logo = "https://media.api-sports.io/basketball/teams/160.png"
        ),
        away = GamesResponse.Response.Teams.Team(
            id = 147,
            name = "Los Angeles Lakers",
            logo = "https://media.api-sports.io/basketball/teams/147.png"
        ),
    ),
    time = "14:00",
    timestamp = 1703973600,
    timezone = "America/Los_Angeles",
    venue = "Delta Center(Salt Lake City)"
)

@Preview
@Composable
private fun GamesListItemPreview() {
    GamesListItem(game = gamesListItem)
}