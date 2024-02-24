package me.blzr.aoc2023.day5

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import me.alllex.parsus.parser.getOrThrow
import me.blzr.aoc2023.day5.Day5Part2.process

class Day5Part2Test : StringSpec({
    "FillMapping" {
        listOf(
            Mapping(20, 10, 10),
            Mapping(30, 20, 10),
            Mapping(40, 40, 10)
        ).fillUp() shouldBe listOf(
            Mapping(0, 0, 10), // Added
            Mapping(20, 10, 10),
            Mapping(30, 20, 10),
            Mapping(30, 30, 10), // Added
            Mapping(40, 40, 10),
            Mapping(50, 50, Long.MAX_VALUE - 50 + 1),
        )
    }

    val text = """
        seeds: 79 14 55 13
        
        seed-to-soil map:
        50 98 2
        52 50 48
        
        soil-to-fertilizer map:
        0 15 37
        37 52 2
        39 0 15
        
        fertilizer-to-water map:
        49 53 8
        0 11 42
        42 0 7
        57 7 4
        
        water-to-light map:
        88 18 7
        18 25 70
        
        light-to-temperature map:
        45 77 23
        81 45 19
        68 64 13
        
        temperature-to-humidity map:
        0 69 1
        1 0 69
        
        humidity-to-location map:
        60 56 37
        56 93 4
    """

    val almanac = AlmanacGrammar2().parse(text).getOrThrow()

    "parse" {
        almanac shouldBe Almanac2(
            seeds = listOf(Range(79, 14), Range(55, 13)),
            seedToSoil = listOf(
                Mapping(50, 98, 2),
                Mapping(52, 50, 48),
            ),
            soilToFertilizer = listOf(
                Mapping(0, 15, 37),
                Mapping(37, 52, 2),
                Mapping(39, 0, 15),
            ),
            fertilizerToWater = listOf(
                Mapping(49, 53, 8),
                Mapping(0, 11, 42),
                Mapping(42, 0, 7),
                Mapping(57, 7, 4),
            ),
            waterToLight = listOf(
                Mapping(88, 18, 7),
                Mapping(18, 25, 70),
            ),
            lightToTemperature = listOf(
                Mapping(45, 77, 23),
                Mapping(81, 45, 19),
                Mapping(68, 64, 13),
            ),
            temperatureToHumidity = listOf(
                Mapping(0, 69, 1),
                Mapping(1, 0, 69),
            ),
            humidityToLocation = listOf(
                Mapping(60, 56, 37),
                Mapping(56, 93, 4),
            )
        )
    }

    "overlap" {
        Range(1, 2) overlap Range(3, 2) shouldBe null
        Range(3, 2) overlap Range(0, 2) shouldBe null

        Range(1, 3) overlap Range(3, 3) shouldBe Range(3, 1)
        Range(1, 3) overlap Range(2, 3) shouldBe Range(2, 2)
        Range(2, 3) overlap Range(1, 3) shouldBe Range(2, 2)
        Range(1, 4) overlap Range(2, 2) shouldBe Range(2, 2)
    }

    "all" {
        almanac.seeds.minOf { it.src } shouldBe 55

        almanac.seeds
            .translate(almanac.seedToSoil).minOf { it.src } shouldBe 57

        almanac.seeds
            .translate(almanac.seedToSoil)
            .translate(almanac.soilToFertilizer)
            .minOf { it.src } shouldBe 84

        almanac.seeds
            .translate(almanac.seedToSoil)
            .translate(almanac.soilToFertilizer)
            .translate(almanac.fertilizerToWater)
            .minOf { it.src } shouldBe 84

        almanac.seeds
            .translate(almanac.seedToSoil)
            .translate(almanac.soilToFertilizer)
            .translate(almanac.fertilizerToWater)
            .translate(almanac.waterToLight)
            .minOf { it.src } shouldBe 77

        almanac.seeds
            .translate(almanac.seedToSoil)
            .translate(almanac.soilToFertilizer)
            .translate(almanac.fertilizerToWater)
            .translate(almanac.waterToLight)
            .translate(almanac.lightToTemperature)
            .minOf { it.src } shouldBe 45

        almanac.seeds
            .translate(almanac.seedToSoil)
            .translate(almanac.soilToFertilizer)
            .translate(almanac.fertilizerToWater)
            .translate(almanac.waterToLight)
            .translate(almanac.lightToTemperature)
            .translate(almanac.temperatureToHumidity)
            .minOf { it.src } shouldBe 46

        almanac.seeds
            .translate(almanac.seedToSoil)
            .translate(almanac.soilToFertilizer)
            .translate(almanac.fertilizerToWater)
            .translate(almanac.waterToLight)
            .translate(almanac.lightToTemperature)
            .translate(almanac.temperatureToHumidity)
            .translate(almanac.humidityToLocation)
            .minOf { it.src } shouldBe 46

        text.process() shouldBe 46
    }
})
