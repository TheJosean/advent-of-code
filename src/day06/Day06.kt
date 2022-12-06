package day06

import readInput

fun main() {

    fun execute(input: String, size: Int): Int {
        val map = mutableMapOf<Char, Int>()
        var lastRep = -1
        input.forEachIndexed { index, c ->
            if (map.containsKey(c) && map.getValue(c) > lastRep) lastRep = map.getValue(c)
            if (index - lastRep == size) return index + 1
            map[c] = index
        }
        return 0
    }

    fun part1(input: String): Int {
        return execute(input, 4)
    }

    fun part2(input: String): Int {
        return execute(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day06/Day06_test")
    check(part1(testInput.first()) == 7)
    check(part2(testInput.first()) == 19)

    val input = readInput("/day06/Day06")
    println(part1(input.first()))
    println(part2(input.first()))
}
