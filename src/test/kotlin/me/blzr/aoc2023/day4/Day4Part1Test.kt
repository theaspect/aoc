package me.blzr.aoc2023.day4

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow

class Day4Part1Test : StringSpec({
    "parser" {
        Day4Part1.grammar.parse("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53").getOrThrow() shouldBe
                Day4Part1.Card(1, setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))

        // We don't bother asserting
        Day4Part1.grammar.parse("Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19").getOrThrow()
        Day4Part1.grammar.parse("Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1").getOrThrow()
        Day4Part1.grammar.parse("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83").getOrThrow()
        Day4Part1.grammar.parse("Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36").getOrThrow()
        Day4Part1.grammar.parse("Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11").getOrThrow()
    }
})
