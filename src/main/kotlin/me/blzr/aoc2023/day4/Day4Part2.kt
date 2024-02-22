package me.blzr.aoc2023.day4

import me.alllex.parsus.parser.Grammar
import me.alllex.parsus.parser.getOrThrow

object Day4Part2 {
    @JvmStatic
    fun main(vararg args: String) {
        println(System.`in`.bufferedReader().lines().toList().process())
    }

    val grammar: Grammar<Card> = CardGrammar()

    fun List<String>.process(): Int =
        this
            .map { grammar.parse(it).getOrThrow() }
            .total()

    fun List<Card>.multiply(): List<Card> {
        val cardById: Map<Int, Card> = this.associateBy { it.id }

        val buffer: ArrayDeque<Card> = ArrayDeque(this)
        val processed: MutableList<Card> = mutableListOf()

        while (buffer.isNotEmpty()) {
            val c: Card = buffer.removeFirst()

            processed.add(c)

            for (i in c.id + 1..c.id + c.intersection) {
                if (i in cardById) buffer.add(cardById[i]!!)
            }
        }

        return processed.sortedBy { it.id }
    }

    // For ease of testig
    fun List<Card>.total(): Int = this.multiply().size
}

