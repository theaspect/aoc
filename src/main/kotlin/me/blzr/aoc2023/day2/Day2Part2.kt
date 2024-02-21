package me.blzr.aoc2023.day2

import me.blzr.aoc2023.day2.Day2Part1.parseLine
import java.util.stream.Stream


object Day2Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().process())
    }

    fun Stream<String>.process(): Int =
        this
            .map(::parseLine)
            .map(Game::reduce)
            .map(GameSet::product)
            .reduce(0, Int::plus)
}
