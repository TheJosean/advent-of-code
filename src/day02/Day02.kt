package day02

import readInput

fun main() {

    fun calculateScore(round: Pair<Int, Int>) = when {
        round.second - 1 == round.first || round.second + 1 < round.first -> round.second + 6 + 1
        round.first == round.second -> round.second + 3 + 1
        else -> round.second + 1
    }

    fun part1(input: List<String>) = input.map {
        val split = it.split(" ")
        Pair(
            when (split[0]) {"A" -> 0; "B" -> 1; else -> 2},
            when (split[1]) {"X" -> 0; "Y" -> 1; else -> 2}
        )
    }.sumOf {
        calculateScore(it)
    }

    fun part2(input: List<String>) = input.map {
        val split = it.split(" ")
        val first = when (split[0]) {"A" -> 0; "B" -> 1; else -> 2}
        val second = when (split[1]) {"X" -> (first + 2) % 3; "Z" -> (first + 1) % 3; else -> first}
        Pair(first, second)
    }.sumOf {
        calculateScore(it)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day02/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("/day02/Day02")
    println(part1(input))
    println(part2(input))
}
