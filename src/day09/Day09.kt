package day09

import readInput
import kotlin.math.absoluteValue
import kotlin.math.min

fun main() {

    data class Coordinate(var x: Int, var y: Int)

    fun getMotions(direction: String, amount: Int): List<Coordinate> {
        val motions = when (direction) {
            "R" -> (1..amount).map { Coordinate(1, 0) }
            "L" -> (-amount..-1).map { Coordinate(-1, 0) }
            "U" -> (1..amount).map { Coordinate(0, 1) }
            "D" -> (-amount..-1).map { Coordinate(0, -1) }
            else -> listOf()
        }
        return motions
    }

    fun getPosition(target: Coordinate, knots: List<Coordinate>): Coordinate? {
        var next = target
        knots.forEach { coordinate ->
            val distance = Coordinate(next.x - coordinate.x, next.y - coordinate.y)
            if (distance.x.absoluteValue > 1 || distance.y.absoluteValue > 1) {
                val x = min(distance.x.absoluteValue, 1)
                val y = min(distance.y.absoluteValue, 1)
                coordinate.x += if (distance.x > 0) x else -x
                coordinate.y += if (distance.y > 0) y else -y
            } else {
                return null
            }
            next = coordinate
        }
        return knots.last().copy()
    }

    fun execute(input: List<String>, knotsCount: Int): Int {
        val head = Coordinate(0, 0)
        val elements = List(knotsCount) { Coordinate(0, 0) }
        return 1 + input.map {
            val (direction, amount) = it.split(' ')
            getMotions(direction, amount.toInt())
        }.flatten().mapNotNull {
            head.x += it.x; head.y += it.y
            getPosition(head, elements)
        }.toSet().size
    }

    fun part1(input: List<String>): Int {
        return execute(input, 1)
    }

    fun part2(input: List<String>): Int {
        return execute(input, 9)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day09/Day09_test")
    check(part1(testInput) == 13)

    val testInput2 = readInput("/day09/Day09_test2")
    check(part2(testInput2) == 36)

    val input = readInput("/day09/Day09")
    println(part1(input))
    println(part2(input))
}