package me.blzr.aoc2023.day6

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.literalToken
import me.alllex.parsus.token.regexToken

object Day6Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().readText().process())
    }

    fun String.process(): Int =
        RaceGrammar2().parse(this).getOrThrow()
            .winners()
}

class RaceGrammar2 : Grammar<Race>() {
    init {
        regexToken("\\s+", ignored = true)
    }

    val time = literalToken("Time:")
    val distance = literalToken("Distance:")
    val num = regexToken("\\d+")

    override val root: Parser<Race> = parser {
        skip(time)
        val time = repeatOneOrMore(num).map { it.text }.joinToString("").toLong()
        skip(distance)
        val distance = repeatOneOrMore(num).map { it.text }.joinToString("").toLong()

        Race(time, distance)
    }
}

