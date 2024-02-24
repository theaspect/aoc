package me.blzr.aoc2023.day5

data class Almanac2(
    private val seeds: List<Range>,
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
            TODO()
//            val soil = seed.translate(seedToSoil)
//            val fertilizer = soil.translate(soilToFertilizer)
//            val water = fertilizer.translate(fertilizerToWater)
//            val light = water.translate(waterToLight)
//            val temperature = light.translate(lightToTemperature)
//            val humidity = temperature.translate(temperatureToHumidity)
//            val location = humidity.translate(humidityToLocation)
//
//            Rule(
//                seed = seed,
//                soil = soil,
//                fertilizer = fertilizer,
//                water = water,
//                light = light,
//                temperature = temperature,
//                humidity = humidity,
//                location = location
//            )
        }
}
