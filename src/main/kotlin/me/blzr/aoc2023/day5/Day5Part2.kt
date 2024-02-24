package me.blzr.aoc2023.day5

import me.alllex.parsus.parser.getOrThrow

object Day5Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().readText().process())
    }

    fun String.process(): Long =
        AlmanacGrammar1().parse(this).getOrThrow()
            .translate()
            .minOf { it.location }
}

