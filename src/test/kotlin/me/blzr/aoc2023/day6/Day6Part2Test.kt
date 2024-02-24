package me.blzr.aoc2023.day6

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow
import me.blzr.aoc2023.day6.Day6Part2.process

class Day6Part2Test : StringSpec({
    val text = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()

    "parse" {
        RaceGrammar2().parse(text).getOrThrow() shouldBe Race(71530, 940200)
    }

    "all" {
        text.process() shouldBe 71503
    }
})
