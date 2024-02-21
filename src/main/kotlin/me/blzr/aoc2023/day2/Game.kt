package me.blzr.aoc2023.day2

data class Game(
    val id: Int,
    val sets: List<GameSet>,
) {
    val isPossible: Boolean
        get() = sets.all(GameSet::isPossible)

    fun reduce(): GameSet =
        sets.reduce(GameSet::minOf)
}
