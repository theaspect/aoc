package me.blzr.aoc2023.day4

import me.alllex.parsus.parser.Grammar
import me.alllex.parsus.parser.parser
import me.alllex.parsus.parser.repeatOneOrMore
import me.alllex.parsus.parser.skip
import me.alllex.parsus.token.literalToken
import me.alllex.parsus.token.regexToken

object Day4Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().toList().process())
    }

    fun List<String>.process(): Int {
        TODO()
    }

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
            val id = cardNum().text.toInt()
            skip(colon)
            val nums = repeatOneOrMore(num).map { it.text.toInt() }.toSet()
            skip(pipe)
            val wins = repeatOneOrMore(num).map { it.text.toInt() }.toSet()

            Card(id, nums, wins)
        }
    }

    data class Card(
        val id: Int,
        val nums: Set<Int>,
        val wins: Set<Int>,
    )
}
