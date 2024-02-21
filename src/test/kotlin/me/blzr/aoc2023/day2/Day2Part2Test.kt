package me.blzr.aoc2023.day2

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day2.Day2Part1.parseLine
import me.blzr.aoc2023.day2.Day2Part2.process
import java.util.stream.Stream

class Day2Part2Test : StringSpec({
    "reduce" {
        parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
            .reduce() shouldBe GameSet(4, 2, 6)
        parseLine("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue")
            .reduce() shouldBe GameSet(1, 3, 4)
        parseLine("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
            .reduce() shouldBe GameSet(20, 13, 6)
        parseLine("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red")
            .reduce() shouldBe GameSet(14, 3, 15)
        parseLine("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
            .reduce() shouldBe GameSet(6, 3, 2)
    }

    "minOf" {
        GameSet(1, 2, 3).minOf(GameSet(4, 1, 1)) shouldBe
                GameSet(4, 2, 3)
    }

    "all" {
        Stream.of(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        ).process() shouldBe 2286
    }
})
