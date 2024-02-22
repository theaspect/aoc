package me.blzr.aoc2023.day5

import me.alllex.parsus.parser.getOrThrow

object Day5Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().readText().process())
    }

    fun String.process(): Long =
        AlmanachGrammar().parse(this).getOrThrow()
            .translate()
            .minOf { it.location }
}

