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
    val seedToSoilFull = seedToSoil.fillUp()
    val soilToFertilizerFull = soilToFertilizer.fillUp()
    val fertilizerToWaterFull = fertilizerToWater.fillUp()
    val waterToLightFull = waterToLight.fillUp()
    val lightToTemperatureFull = lightToTemperature.fillUp()
    val temperatureToHumidityFull = temperatureToHumidity.fillUp()
    val humidityToLocationFull = humidityToLocation.fillUp()

    fun translate(): Long = seeds
        .translate(seedToSoilFull)
        .translate(soilToFertilizerFull)
        .translate(fertilizerToWaterFull)
        .translate(waterToLightFull)
        .translate(lightToTemperatureFull)
        .translate(temperatureToHumidityFull)
        .translate(humidityToLocationFull)
        .minOf { it.src }
}
