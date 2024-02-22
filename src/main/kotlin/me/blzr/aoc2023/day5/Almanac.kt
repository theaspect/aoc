package me.blzr.aoc2023.day5

import me.alllex.parsus.parser.*
import me.alllex.parsus.token.literalToken
import me.alllex.parsus.token.regexToken

data class Almanac(
    private val seeds: List<Long>,
    private val seedToSoil: List<Mapping>,
    private val soilToFertilizer: List<Mapping>,
    private val fertilizerToWater: List<Mapping>,
    private val waterToLight: List<Mapping>,
    private val lightToTemperature: List<Mapping>,
    private val temperatureToHumidity: List<Mapping>,
    private val humidityToLocation: List<Mapping>,
) {
    fun translate() =
        seeds.map { seed ->
            val soil = seed.translate(seedToSoil)
            val fertilizer = soil.translate(soilToFertilizer)
            val water = fertilizer.translate(fertilizerToWater)
            val light = water.translate(waterToLight)
            val temperature = light.translate(lightToTemperature)
            val humidity = temperature.translate(temperatureToHumidity)
            val location = humidity.translate(humidityToLocation)

            Rule(
                seed = seed,
                soil = soil,
                fertilizer = fertilizer,
                water = water,
                light = light,
                temperature = temperature,
                humidity = humidity,
                location = location
            )
        }
}

data class Rule(
    val seed: Long,
    val soil: Long,
    val fertilizer: Long,
    val water: Long,
    val light: Long,
    val temperature: Long,
    val humidity: Long,
    val location: Long,
)

data class Mapping(
    val dst: Long,
    val src: Long,
    val len: Long,
) {
    val delta = dst - src
    val range = src..<src + len

    fun tryTranslate(src: Long): Long? =
        when {
            src in range -> src + delta
            else -> null
        }
}

fun Long.translate(mapping: List<Mapping>): Long =
    mapping.firstNotNullOfOrNull { it.tryTranslate(this@translate) } ?: this@translate

class AlmanachGrammar : Grammar<Almanac>() {
    init {
        regexToken("\\s+", ignored = true)
    }

    private val seeds = literalToken("seeds:")
    private val s2s = literalToken("seed-to-soil map:")
    private val s2f = literalToken("soil-to-fertilizer map:")
    private val f2w = literalToken("fertilizer-to-water map:")
    private val w2l = literalToken("water-to-light map:")
    private val l2t = literalToken("light-to-temperature map:")
    private val t2h = literalToken("temperature-to-humidity map:")
    private val h2l = literalToken("humidity-to-location map:")
    private val num = regexToken("\\d+")
    private val triple = parser {
        val (dst, src, len) = repeat(num, 3, 3).map { it.text.toLong() }
        Mapping(dst, src, len)
    }

    override val root: Parser<Almanac> = parser {
        skip(seeds)
        val seeds = repeatOneOrMore(num).map { it.text.toLong() }
        skip(s2s)
        val s2sMap = repeatZeroOrMore(triple)
        skip(s2f)
        val s2fMap = repeatZeroOrMore(triple)
        skip(f2w)
        val f2wMap = repeatZeroOrMore(triple)
        skip(w2l)
        val w2lMap = repeatZeroOrMore(triple)
        skip(l2t)
        val l2tMap = repeatZeroOrMore(triple)
        skip(t2h)
        val t2hMap = repeatZeroOrMore(triple)
        skip(h2l)
        val h2lMap = repeatZeroOrMore(triple)

        Almanac(
            seeds = seeds,
            seedToSoil = s2sMap,
            soilToFertilizer = s2fMap,
            fertilizerToWater = f2wMap,
            waterToLight = w2lMap,
            lightToTemperature = l2tMap,
            temperatureToHumidity = t2hMap,
            humidityToLocation = h2lMap,
        )
    }
}
