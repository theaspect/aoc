package me.blzr.aoc2023.day7

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.regexToken
import me.blzr.aoc2023.day7.Hand.Companion.toHand

object Day7Part1 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lineSequence().process())
    }

    val parser = HandGrammar()

    fun Sequence<String>.parsed() =
        this.map { parser.parse(it).getOrThrow() }

    fun Sequence<Player>.ranked() = this
        .sortedBy { it.hand }
        .windowed(2, partialWindows = true) { window ->
            when {
                (window.size == 2) -> {
                    val (a, b) = window
                    val cmp = a.hand.compareTo(b.hand)
                    when {
                        cmp == 0 -> b.rank = a.rank
                        // Note it could be not only [-1, 0, 1] but other values too
                        cmp < 0 -> b.rank = a.rank + 1
                        else -> throw IllegalStateException("List should be ordered")
                    }
                    a
                }

                else -> window[0]
            }
        }

    fun Sequence<String>.process(): Long = this
        .parsed()
        .ranked()
        .sumOf { it.rank * it.bid }
}

class HandGrammar : Grammar<Player>() {
    init {
        regexToken("\\s+", ignored = true)
    }

    private val card = regexToken("[23456789TJQKA]")
    private val bid = regexToken("\\d+")

    override val root: Parser<Player> = parser {
        val hand = Cards(repeat(card, 5, 5).map { Card.valueOf(it.text) })
        val bid: Long = bid().text.toLong()

        Player(hand, bid)
    }
}

data class Player(
    val hand: Cards,
    val bid: Long,
    var rank: Int = 1,
)

data class Cards(
    val cards: List<Card>
) : Comparable<Cards> {
    private val hand: Hand = cards.toHand()

    override fun compareTo(other: Cards): Int =
        when {
            this.hand != other.hand -> this.hand.compareTo(other.hand)
            else -> this.cards.zip(other.cards)
                .fold(0) { acc, (a, b) ->
                    when {
                        acc != 0 -> acc
                        else -> a.compareTo(b)
                    }
                }
        }
}

enum class Card : Comparable<Card> {
    `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, T,
    J, Q, K, A,
    ;
}

enum class Hand {
    HIGH_HAND,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND,
    ;

    companion object {
        fun List<Card>.toHand(): Hand {
            val groupAndSort: List<Pair<Card, Int>> =
                this.groupingBy { it }.eachCount()
                    .map { (k, v) -> k to v }
                    .sortedByDescending { (k, v) -> v }

            return when {
                groupAndSort[0].second == 5 -> FIVE_OF_A_KIND
                groupAndSort[0].second == 4 -> FOUR_OF_A_KIND
                groupAndSort[0].second == 3 && groupAndSort[1].second == 2 -> FULL_HOUSE
                groupAndSort[0].second == 3 -> THREE_OF_A_KIND
                groupAndSort[0].second == 2 && groupAndSort[1].second == 2 -> TWO_PAIR
                groupAndSort[0].second == 2 -> ONE_PAIR
                else -> HIGH_HAND
            }
        }
    }
}
