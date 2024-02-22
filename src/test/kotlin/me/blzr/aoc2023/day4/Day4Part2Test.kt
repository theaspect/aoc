package me.blzr.aoc2023.day4

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow
import me.blzr.aoc2023.day4.Day4Part2.grammar
import me.blzr.aoc2023.day4.Day4Part2.multiply
import me.blzr.aoc2023.day4.Day4Part2.total

class Day4Part2Test : StringSpec({
    val cards: List<Card> = listOf(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    ).map { grammar.parse(it).getOrThrow() }

    /**
     * Once all the originals and copies have been processed,
     * you end up with 1 instance of card 1,
     * 2 instances of card 2,
     * 4 instances of card 3,
     * 8 instances of card 4,
     * 14 instances of card 5,
     * and 1 instance of card 6.
     * In total, this example pile of scratchcards causes you to ultimately have 30 scratchcards!
     */
    "multiplication" {
        cards.multiply().groupingBy { it.id }.eachCount() shouldBe
                mapOf(
                    1 to 1,
                    2 to 2,
                    3 to 4,
                    4 to 8,
                    5 to 14,
                    6 to 1,
                )
    }

    "all" {
        cards.total() shouldBe 30
    }
})
