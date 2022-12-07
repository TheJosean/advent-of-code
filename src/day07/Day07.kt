package day07

import readInput

fun main() {

    data class Directory(val size: Int, val index: Int)

    fun parseDirectories(input: List<String>, startingIndex: Int): List<Directory> {
        val items = input.subList(startingIndex + 2, input.size).takeWhile { !it.startsWith('$') }
        var size = 0; var index = -1
        val directories = mutableListOf<Directory>()

        items.forEach {
            if (it.startsWith("dir")) {
                directories += parseDirectories(input, if (index == - 1) items.size + startingIndex + 2 else index)
                val lastDirectory = directories.last()
                index = lastDirectory.index + 1
                size += lastDirectory.size
            } else {
                size += it.split(" ").first().toInt()
            }
        }

        return directories.plus(Directory(size,  if (index == - 1) items.size + startingIndex + 2 else index))
    }

    fun part1(input: List<String>): Int {
        return parseDirectories(input, 0)
            .filter { it.size < 100000 }
            .sumOf { it.size}
    }

    fun part2(input: List<String>): Int {
        val discSpace = 70000000
        val neededSpace = 30000000
        val directories = parseDirectories(input, 0)
        val totalUsage = directories.last().size
        val minUsage = neededSpace - (discSpace - totalUsage)
        return directories.map { it.size }.filter { it > minUsage }.min()

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day07/Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("/day07/Day07")
    println(part1(input))
    println(part2(input))
}
