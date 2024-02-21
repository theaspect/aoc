package me.blzr.aoc2023.day2

import java.util.stream.Stream


object Day2Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().process())
    }

    fun Stream<String>.process(): Int =
        this
            .map(::parseLine)
            .filter(Game::isPossible)
            .map(Game::id)
            .reduce(0, Int::plus)

    fun parseLine(line: String): Game {
        // Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        val (gameId, gameSets) = line.split(": ")
        val (_, id) = gameId.split(" ")

        val gameSetList: List<GameSet> =
            gameSets.split("; ").map(::parseGameSet)

        return Game(id.toInt(), gameSetList)
    }

    // 8 green, 6 blue, 20 red
    fun parseGameSet(gameSetList: String): GameSet =
        gameSetList
            .split(", ")
            .map { valueColor ->
                val (value, color) = valueColor.split(" ")
                GameSet.toGameSet(value.toInt(), color)
            }
            .reduce(GameSet::plus)
}

data class Game(
    val id: Int,
    val sets: List<GameSet>,
) {
    val isPossible: Boolean
        get() = sets.all(GameSet::isPossible)
}

data class GameSet(
    val red: Int,
    val green: Int,
    val blue: Int,
) {
    val isPossible: Boolean
        get() = red <= 12 && green <= 13 && blue <= 14

    operator fun plus(other: GameSet): GameSet =
        GameSet(
            this.red + other.red,
            this.green + other.green,
            this.blue + other.blue,
        )

    companion object {
        fun toGameSet(value: Int, color: String): GameSet =
            when (color) {
                "red" -> GameSet(value, 0, 0)
                "green" -> GameSet(0, value, 0)
                "blue" -> GameSet(0, 0, value)
                else -> throw IllegalArgumentException("Unknown color $color")
            }
    }
}
