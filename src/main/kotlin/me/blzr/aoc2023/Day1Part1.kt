package me.blzr.aoc2023

import kotlin.streams.asSequence

fun main() {
    println(System.`in`.bufferedReader().lines().asSequence().process())
}

fun Sequence<String>.process(): Int =
    this.map { getPair(it) }.sum()

fun getPair(s: String): Int {
    val first = s.find { it in '0'..'9' }!! - '0'
    val last = s.findLast { it in '0'..'9' }!! - '0'
    return first * 10 + last
}
