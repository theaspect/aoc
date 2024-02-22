package me.blzr.aoc2023.day4

import me.alllex.parsus.parser.Grammar
import me.alllex.parsus.parser.getOrThrow
import java.util.stream.Stream

object Day4Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().process())
    }

    fun Stream<String>.process(): Int = this
        .map { grammar.parse(it).getOrThrow() }
        .collect()

    fun Stream<Card>.collect(): Int = this
        .map { it.intersection }
        .map {
            when {
                it.isEmpty() -> 0
                else -> Math.pow(2.0, it.size - 1.0).toInt()
            }
        }
        .reduce(Int::plus)
        .get()


    val grammar: Grammar<Card> = CardGrammar()
}

