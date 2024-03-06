package me.blzr.aoc2023.day7

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day7.Card.*
import me.blzr.aoc2023.day7.Day7Part1.parsed
import me.blzr.aoc2023.day7.Day7Part1.process
import me.blzr.aoc2023.day7.Day7Part1.ranked

class Day7Part1Test : StringSpec({
    val players = sequenceOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28",
        "KTJJT 220",
        "QQQJA 483",
    )

    val parser = HandGrammar()

    "parse" {
        players.parsed().toList() shouldBe listOf(
            Player(Cards(listOf(`3`, `2`, T, `3`, K)), 765),
            Player(Cards(listOf(T, `5`, `5`, J, `5`)), 684),
            Player(Cards(listOf(K, K, `6`, `7`, `7`)), 28),
            Player(Cards(listOf(K, T, J, J, T)), 220),
            Player(Cards(listOf(Q, Q, Q, J, A)), 483),
        )
    }

    "ranked" {
        players.parsed().ranked().toList() shouldBe listOf(
            Player(Cards(listOf(`3`, `2`, T, `3`, K)), 765, 1),
            Player(Cards(listOf(K, T, J, J, T)), 220, 2),
            Player(Cards(listOf(K, K, `6`, `7`, `7`)), 28, 3),
            Player(Cards(listOf(T, `5`, `5`, J, `5`)), 684, 4),
            Player(Cards(listOf(Q, Q, Q, J, A)), 483, 5),
        )
    }

    "same rank" {
        sequenceOf(
            Player(Cards(listOf(`3`, `2`, T, `3`, K)), 1),
            Player(Cards(listOf(`3`, `2`, T, `3`, K)), 10),
        ).ranked().toList() shouldBe listOf(
            Player(Cards(listOf(`3`, `2`, T, `3`, K)), 1, 1),
            Player(Cards(listOf(`3`, `2`, T, `3`, K)), 10, 1),
        )
    }

    "full" {
        players.process() shouldBe 6440
    }
})
