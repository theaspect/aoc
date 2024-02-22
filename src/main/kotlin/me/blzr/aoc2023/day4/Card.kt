package me.blzr.aoc2023.day4

data class Card(
    val id: Int,
    val wins: Set<Int>,
    val nums: Set<Int>,
) {
    val intersection: Int = nums.intersect(wins).size
}
