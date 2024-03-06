package me.blzr.aoc2023.day7

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day7.Card2.*
import me.blzr.aoc2023.day7.Day7Part2.parsed
import me.blzr.aoc2023.day7.Day7Part2.process
import me.blzr.aoc2023.day7.Day7Part2.ranked

class Day7Part2Test : StringSpec({
    val players = sequenceOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28",
        "KTJJT 220",
        "QQQJA 483",
    )

    val parser = HandGrammar2()

    "parse" {
        players.parsed().toList() shouldBe listOf(
            Player2(Cards2(listOf(`3`, `2`, T, `3`, K)), 765),
            Player2(Cards2(listOf(T, `5`, `5`, J, `5`)), 684),
            Player2(Cards2(listOf(K, K, `6`, `7`, `7`)), 28),
            Player2(Cards2(listOf(K, T, J, J, T)), 220),
            Player2(Cards2(listOf(Q, Q, Q, J, A)), 483),
        )
    }

    "ranked" {
        players.parsed().ranked().toList() shouldBe listOf(
            Player2(Cards2(listOf(`3`, `2`, T, `3`, K)), 765, 1),
            Player2(Cards2(listOf(K, K, `6`, `7`, `7`)), 28, 2),
            Player2(Cards2(listOf(T, `5`, `5`, J, `5`)), 684, 3),
            Player2(Cards2(listOf(Q, Q, Q, J, A)), 483, 4),
            Player2(Cards2(listOf(K, T, J, J, T)), 220, 5),
        )
    }

    "same rank" {
        sequenceOf(
            Player2(Cards2(listOf(`3`, `2`, T, `3`, K)), 1),
            Player2(Cards2(listOf(`3`, `2`, T, `3`, K)), 10),
        ).ranked().toList() shouldBe listOf(
            Player2(Cards2(listOf(`3`, `2`, T, `3`, K)), 1, 1),
            Player2(Cards2(listOf(`3`, `2`, T, `3`, K)), 10, 1),
        )
    }

    "full" {
        players.process() shouldBe 5905
    }
})
