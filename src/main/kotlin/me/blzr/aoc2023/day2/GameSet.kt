package me.blzr.aoc2023.day2

data class GameSet(
    val red: Int,
    val green: Int,
    val blue: Int,
) {
    val isPossible: Boolean
        get() = red <= 12 && green <= 13 && blue <= 14

    operator fun plus(other: GameSet): GameSet =
        GameSet(
            this.red + other.red,
            this.green + other.green,
            this.blue + other.blue,
        )

    fun minOf(other: GameSet): GameSet =
        GameSet(
            maxOf(this.red, other.red),
            maxOf(this.green, other.green),
            maxOf(this.blue, other.blue),
        )

    val product: Int = red * green * blue

    companion object {
        fun toGameSet(value: Int, color: String): GameSet =
            when (color) {
                "red" -> GameSet(value, 0, 0)
                "green" -> GameSet(0, value, 0)
                "blue" -> GameSet(0, 0, value)
                else -> throw IllegalArgumentException("Unknown color $color")
            }
    }
}
