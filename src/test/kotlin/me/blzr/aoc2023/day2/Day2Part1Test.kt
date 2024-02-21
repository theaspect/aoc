package me.blzr.aoc2023.day2

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.blzr.aoc2023.day2.Day2Part1.parseGameSet
import me.blzr.aoc2023.day2.Day2Part1.parseLine
import me.blzr.aoc2023.day2.Day2Part1.process
import java.util.stream.Stream

class Day2Part1Test : StringSpec({
    "overall" {
        Stream.of(
            "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
            "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
            "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
            "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
            "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green",
        ).process() shouldBe 8
    }

    "plus" {
        GameSet(1, 0, 0) +
                GameSet(0, 2, 0) +
                GameSet(0, 0, 3) shouldBe
                GameSet(1, 2, 3)

    }

    "toGameSet" {
        GameSet.toGameSet(1, "red") shouldBe GameSet(1, 0, 0)
        GameSet.toGameSet(10, "green") shouldBe GameSet(0, 10, 0)
        GameSet.toGameSet(100, "blue") shouldBe GameSet(0, 0, 100)
    }

    "parseGameSet" {
        parseGameSet("8 green, 6 blue, 20 red") shouldBe GameSet(20, 8, 6)
        parseGameSet("5 blue, 4 red, 13 green") shouldBe GameSet(4, 13, 5)
        parseGameSet("5 green, 1 red") shouldBe GameSet(1, 5, 0)
    }

    "parseLine" {
        parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green") shouldBe
                Game(
                    1, listOf(
                        GameSet(4, 0, 3),
                        GameSet(1, 2, 6),
                        GameSet(0, 2, 0),
                    )
                )
        parseLine("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue") shouldBe
                Game(
                    2, listOf(
                        GameSet(0, 2, 1),
                        GameSet(1, 3, 4),
                        GameSet(0, 1, 1),
                    )
                )
        parseLine("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red") shouldBe
                Game(
                    3, listOf(
                        GameSet(20, 8, 6),
                        GameSet(4, 13, 5),
                        GameSet(1, 5, 0),
                    )
                )
        parseLine("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red") shouldBe
                Game(
                    4, listOf(
                        GameSet(3, 1, 6),
                        GameSet(6, 3, 0),
                        GameSet(14, 3, 15),
                    )
                )
        parseLine("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green") shouldBe
                Game(
                    5, listOf(
                        GameSet(6, 3, 1),
                        GameSet(1, 2, 2),
                    )
                )
    }

    "isValid" {
        parseLine("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").isPossible shouldBe true
        parseLine("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue").isPossible shouldBe true
        parseLine("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red").isPossible shouldBe false
        parseLine("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red").isPossible shouldBe false
        parseLine("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green").isPossible shouldBe true
    }
})
