package me.blzr.aoc2023.day4

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.literalToken
import me.alllex.parsus.token.regexToken
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

    /**
     * Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
     */
    val grammar: Grammar<Card> = object : Grammar<Card>() {
        init {
            regexToken("\\s+", ignored = true)
        }

        val tokenCard by literalToken("Card")
        val cardNum by regexToken("\\d+")
        val colon by literalToken(":")
        val pipe by literalToken("|")
        val num by regexToken("\\d{1,2}")

        override val root by parser {
            skip(tokenCard)
            val id: Int = cardNum().text.toInt()
            skip(colon)
            val wins: Set<Int> = repeatOneOrMore(num).map { it.text.toInt() }.toSet()
            skip(pipe)
            val nums: Set<Int> = repeatOneOrMore(num).map { it.text.toInt() }.toSet()

            Card(id, wins, nums)
        }
    }

    data class Card(
        val id: Int,
        val wins: Set<Int>,
        val nums: Set<Int>,
    ) {
        val intersection: Set<Int>
            get() = nums.intersect(wins)
    }
}
