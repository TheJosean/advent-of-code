package day08

import readInput

fun main() {

    fun List<List<Char>>.visibleLeft(i: Int, j: Int): Boolean {
        val base = this[i][j]
        for (x in j - 1 downTo 0) {
            if (this[i][x] >= base) return false
        }
        return true
    }

    fun List<List<Char>>.visibleRight(i: Int, j: Int): Boolean {
        val base = this[i][j]
        for (x in j + 1 until this[i].size) {
            if (this[i][x] >= base) return false
        }
        return true
    }

    fun List<List<Char>>.visibleTop(i: Int, j: Int): Boolean {
        val base = this[i][j]
        for (x in i - 1 downTo 0) {
            if (this[x][j] >= base) return false
        }
        return true
    }

    fun List<List<Char>>.visibleBottom(i: Int, j: Int): Boolean {
        val base = this[i][j]
        for (x in i + 1 until size) {
            if (this[x][j] >= base) return false
        }
        return true
    }

    fun List<List<Char>>.countLeft(i: Int, j: Int): Int {
        val base = this[i][j]
        var count = 0
        for (x in j - 1 downTo 0) {
            if (base > this[i][x]) count++
            else return ++count
        }
        return count
    }

    fun List<List<Char>>.countRight(i: Int, j: Int): Int {
        val base = this[i][j]
        var count = 0
        for (x in j + 1 until this[i].size) {
            if (base > this[i][x]) count++
            else return ++count
        }
        return count
    }

    fun List<List<Char>>.countTop(i: Int, j: Int): Int {
        val base = this[i][j]
        var count = 0
        for (x in i - 1 downTo 0) {
            if (base > this[x][j]) count++
            else return ++count
        }
        return count
    }

    fun List<List<Char>>.countBottom(i: Int, j: Int): Int {
        val base = this[i][j]
        var count = 0
        for (x in i + 1 until size) {
            if (base > this[x][j]) count++
            else return ++count
        }
        return count
    }

    fun List<List<Char>>.count(i: Int, j: Int): Int {
        if (i == 0 || j == 0 || i == size || j == this[i].size) return 0
        return countLeft(i, j) * countRight(i, j) * countTop(i, j) * countBottom(i, j)
    }

    fun List<List<Char>>.visible(i: Int, j: Int) =
        i == 0 || j == 0 ||
                visibleLeft(i, j) || visibleRight(i, j) || visibleTop(i, j) || visibleBottom(i, j)

    fun part1(input: List<List<Char>>) =
        input.mapIndexed { i, row ->
            row.mapIndexed { j, _ -> input.visible(i, j) }.count { it }
        }.sum()

    fun part2(input: List<List<Char>>) =
        input.mapIndexed { i, row ->
            row.mapIndexed { j, _ -> input.count(i, j) }.max()
        }.max()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day08/Day08_test")
    check(part1(testInput.map { it.toList() }) == 21)
    check(part2(testInput.map { it.toList() }) == 8)

    val input = readInput("/day08/Day08")
    println(part1(input.map { it.toList() }))
    println(part2(input.map { it.toList() }))
}
