package me.blzr.aoc2023.day6

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.literalToken
import me.alllex.parsus.token.regexToken

object Day6Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().readText().process())
    }

    fun String.process(): Int =
        RaceGrammar().parse(this).getOrThrow()
            .process()

    fun List<Race>.process(): Int = TODO()
}

class RaceGrammar : Grammar<List<Race>>() {
    init {
        regexToken("\\s+", ignored = true)
    }

    val time = literalToken("Time:")
    val distance = literalToken("Distance:")
    val num = regexToken("\\d+")

    override val root: Parser<List<Race>> = parser {
        skip(time)
        val times = repeatOneOrMore(num).map { it.text.toInt() }
        skip(distance)
        val distance = repeatOneOrMore(num).map { it.text.toInt() }

        times.zip(distance).map { (time, distance) ->
            Race(time, distance)
        }

    }
}

data class Race(
    val time: Int,
    val record: Int,
)
