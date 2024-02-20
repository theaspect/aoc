package me.blzr.aoc2023.day1

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day1.Day1Part2.anyPrefix
import me.blzr.aoc2023.day1.Day1Part2.convertBackward
import me.blzr.aoc2023.day1.Day1Part2.convertForward
import me.blzr.aoc2023.day1.Day1Part2.getPairReplacing
import me.blzr.aoc2023.day1.Day1Part2.numbers
import me.blzr.aoc2023.day1.Day1Part2.process
import java.util.stream.Stream

class Day1Part2KtTest : StringSpec({
    "test prefix" {
        numbers.keys.anyPrefix("t") shouldBe true
        numbers.keys.anyPrefix("tw") shouldBe true
        numbers.keys.anyPrefix("two") shouldBe true
        numbers.keys.anyPrefix("twone") shouldBe false
    }

    "test convert forward" {
        convertForward("two1nine") shouldBe 2
        convertForward("eightwothree") shouldBe 8
        convertForward("abcone2threexyz") shouldBe 1
        convertForward("xtwone3four") shouldBe 2
        convertForward("4nineeightseven2") shouldBe 4
        convertForward("zoneight234") shouldBe 1
        convertForward("7pqrstsixteen") shouldBe 7
    }

    "test convert backward" {
        convertBackward("two1nine") shouldBe 9
        convertBackward("eightwothree") shouldBe 3
        convertBackward("abcone2threexyz") shouldBe 3
        convertBackward("xtwone3four") shouldBe 4
        convertBackward("4nineeightseven2") shouldBe 2
        convertBackward("zoneight234") shouldBe 4
        convertBackward("7pqrstsixteen") shouldBe 6
    }

    "get pairs" {
        getPairReplacing("two1nine") shouldBe 29
        getPairReplacing("eightwothree") shouldBe 83
        getPairReplacing("abcone2threexyz") shouldBe 13
        getPairReplacing("xtwone3four") shouldBe 24
        getPairReplacing("4nineeightseven2") shouldBe 42
        getPairReplacing("zoneight234") shouldBe 14
        getPairReplacing("7pqrstsixteen") shouldBe 76
    }

    "additional cases" {
        getPairReplacing("6oneightskl") shouldBe 68
        getPairReplacing("nleightwo7") shouldBe 87
    }

    "whole" {
        Stream.of(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
        ).process() shouldBe 281
    }

    "reverse" {
        "abc".reversed() shouldBe "cba"
    }
})
