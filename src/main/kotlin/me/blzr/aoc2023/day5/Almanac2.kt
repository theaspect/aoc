package me.blzr.aoc2023.day5

data class Almanac2(
    val seeds: List<Range>,
    val seedToSoil: List<Mapping>,
    val soilToFertilizer: List<Mapping>,
    val fertilizerToWater: List<Mapping>,
    val waterToLight: List<Mapping>,
    val lightToTemperature: List<Mapping>,
    val temperatureToHumidity: List<Mapping>,
    val humidityToLocation: List<Mapping>,
) {
    fun translate(): Long = seeds
        .translate(seedToSoil)
        .translate(soilToFertilizer)
        .translate(fertilizerToWater)
        .translate(waterToLight)
        .translate(lightToTemperature)
        .translate(temperatureToHumidity)
        .translate(humidityToLocation)
        .minBy { it.src }
        .src
}
