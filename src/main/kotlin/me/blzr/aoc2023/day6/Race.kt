package me.blzr.aoc2023.day6

data class Race(
    val time: Long,
    val record: Long,
) {
    fun winners(): Int =
        sequence {
            for (i in 0..<time) {
                yield(i * (time - i))
            }
        }.filter { it > record }.count()
}
