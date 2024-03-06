package me.blzr.aoc2023.day7

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.regexToken
import me.blzr.aoc2023.day7.Hand2.Companion.toHand2

object Day7Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lineSequence().process())
    }

    val parser = HandGrammar2()

    fun Sequence<String>.parsed() =
        this.map { parser.parse(it).getOrThrow() }

    fun Sequence<Player2>.ranked() = this
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

class HandGrammar2 : Grammar<Player2>() {
    init {
        regexToken("\\s+", ignored = true)
    }

    private val card = regexToken("[23456789TJQKA]")
    private val bid = regexToken("\\d+")

    override val root: Parser<Player2> = parser {
        val hand = Cards2(repeat(card, 5, 5).map { Card2.valueOf(it.text) })
        val bid: Long = bid().text.toLong()

        Player2(hand, bid)
    }
}

data class Player2(
    val hand: Cards2,
    val bid: Long,
    var rank: Int = 1,
)

data class Cards2(
    val cards: List<Card2>
) : Comparable<Cards2> {
    private val hand: Hand2 = cards.toHand2()

    override fun compareTo(other: Cards2): Int =
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

enum class Card2 : Comparable<Card2> {
    J, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, T,
    Q, K, A,
    ;
}

enum class Hand2 {
    HIGH_HAND,
    ONE_PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND,
    ;

    companion object {
        fun List<Card2>.toHand2(): Hand2 {
            val groupAndSort: List<Pair<Card2, Int>> =
                this.groupingBy { it }.eachCount()
                    .map { (k, v) -> k to v }
                    .sortedByDescending { (k, v) -> v }

            val indexOfJoker = groupAndSort.indexOfFirst { it.first == Card2.J }

            // Additional processing
            val groupAndSortJocker = when {
                // We have 5J
                indexOfJoker == 0 && groupAndSort[0].second == 5 -> groupAndSort
                // Attach J to the second
                indexOfJoker == 0 ->
                    listOf(
                        groupAndSort[1].copy(second = groupAndSort[0].second + groupAndSort[1].second),
                    ) + groupAndSort.drop(2)

                // Attach jocker to the first
                indexOfJoker > 0 ->
                    listOf(groupAndSort[0].copy(second = groupAndSort[0].second + groupAndSort[indexOfJoker].second)) +
                            groupAndSort.subList(1, indexOfJoker) +
                            groupAndSort.subList(indexOfJoker + 1, groupAndSort.size)

                else -> groupAndSort
            }

            return when {
                groupAndSortJocker[0].second == 5 -> FIVE_OF_A_KIND
                groupAndSortJocker[0].second == 4 -> FOUR_OF_A_KIND
                groupAndSortJocker[0].second == 3 && groupAndSortJocker[1].second == 2 -> FULL_HOUSE
                groupAndSortJocker[0].second == 3 -> THREE_OF_A_KIND
                groupAndSortJocker[0].second == 2 && groupAndSortJocker[1].second == 2 -> TWO_PAIR
                groupAndSortJocker[0].second == 2 -> ONE_PAIR
                else -> HIGH_HAND
            }
        }
    }
}
