package me.blzr.aoc2023.day6

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow
import me.blzr.aoc2023.day6.Day6Part1.process

class Day6Part1Test : StringSpec({
    val text = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()

    "parse" {
        RaceGrammar().parse(text).getOrThrow() shouldBe listOf(
            Race(7, 9),
            Race(15, 40),
            Race(30, 200),
        )
    }

    "winners" {
        Race(7, 9).winners() shouldBe 4
        Race(15, 40).winners() shouldBe 8
        Race(30, 200).winners() shouldBe 9
    }

    "all" {
        text.process() shouldBe 288
    }

})
