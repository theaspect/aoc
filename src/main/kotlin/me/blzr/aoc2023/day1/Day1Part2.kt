package me.blzr.aoc2023.day1

import java.util.stream.Stream

fun main() {
    println(System.`in`.bufferedReader().lines().process())
}

fun Stream<String>.process(): Int =
    this.map { getPairReplacing(it) }.reduce(0, Int::plus)

fun getPairReplacing(s: String): Int {
    val first = convert(s).find { it in '0'..'9' }!! - '0'
    val last = convert(s).findLast { it in '0'..'9' }!! - '0'
    return first * 10 + last
}

val numbers = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun convert(s: String): String {
    val newLine = StringBuilder()
    val buffer = StringBuilder()

    for (c in s) {
        buffer.append(c)

        if (!numbers.keys.anyPrefix(buffer)) {
            newLine.append(buffer)
            buffer.clear()
        } else if (buffer.toString() in numbers.keys) {
            newLine.append(numbers[buffer.toString()])
            buffer.clear()
        }
    }

    return newLine.toString()
}

fun Set<String>.anyPrefix(prefix: CharSequence): Boolean =
    this.any { it.startsWith(prefix) }
