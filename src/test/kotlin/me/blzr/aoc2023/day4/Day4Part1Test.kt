package me.blzr.aoc2023.day4

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow
import me.blzr.aoc2023.day4.Day4Part1.collect
import me.blzr.aoc2023.day4.Day4Part1.grammar

class Day4Part1Test : StringSpec({
    val cards: List<Card> = listOf(
        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
    ).map { grammar.parse(it).getOrThrow() }

    "parser" {
        cards[0] shouldBe Card(1, setOf(41, 48, 83, 86, 17), setOf(83, 86, 6, 31, 17, 9, 48, 53))
    }

    "intersection" {
        cards[0].intersection shouldBe 4
        cards[1].intersection shouldBe 2
        cards[2].intersection shouldBe 2
        cards[3].intersection shouldBe 1
        cards[4].intersection shouldBe 0
        cards[5].intersection shouldBe 0
    }

    "all" {
        cards.stream().collect() shouldBe 13
    }
})
