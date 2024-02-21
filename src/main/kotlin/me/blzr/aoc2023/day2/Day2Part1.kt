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

