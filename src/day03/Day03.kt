package day03

import readInput

fun main() {

    fun getCharValue(char: Char) =
        if (char.isLowerCase()) char - 'a' + 1 else char - 'A' + 27

    fun part1(input: List<String>) =
        input.sumOf {
            val set = HashSet<Char>()
            it.forEachIndexed { index, c ->
                if (index < it.length / 2) {
                    set.add(c)
                }
                if (index >= it.length / 2 && set.contains(c)) {
                    return@sumOf getCharValue(c)
                }
            }
            return@sumOf 0
        }

    fun part2(input: List<String>) =
        input.chunked(3)
            .sumOf { strings ->
                val map = HashMap<Char, Int>()
                strings.forEachIndexed { index, s ->
                    s.forEach {
                        if (map.containsKey(it)) {
                            if (index - 1 == map[it]) {
                                map[it] = index
                            }
                        } else {
                            map[it] = 0 -index
                        }
                    }
                }
                val char = map.entries.first { it.value + 1 == strings.size }.key
                return@sumOf getCharValue(char)
            }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("/day03/Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("/day03/Day03")
    println(part1(input))
    println(part2(input))
}
