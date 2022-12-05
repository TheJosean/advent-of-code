import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

fun splitList(list: List<String>): List<List<String>> {
    return list.flatMapIndexed { index, s ->
        when {
            index == 0 || index == list.lastIndex -> listOf(index)
            s.isEmpty() -> listOf(index - 1, index + 1)
            else -> emptyList()
        }
    }.windowed(size = 2, step = 2) { (from, to) -> list.slice(from..to) }
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
