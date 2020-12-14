package day14

import support.downloadInput

fun main() {

    //println(solve(downloadInput(14, 2)))
    println("--------------------------")
    println(solve(downloadInput(14)))

}

fun solve(input: List<String>): Any {

    val mem = mutableMapOf<Long, Long>()
    var mask = ""

    input.forEach {
        if (it.contains('[')) {
            val key = it.split(" = ")[0].split('[')[1].split(']').first().toLong()
            val keys = getArrdList(mask, listOf(key))
            val finVal = it.split(" = ")[1].toLong()

            keys.forEach {
                mem.put(it, finVal)
            }
        } else {
            mask = it.split(" = ")[1]
        }
    }
    var sum = 0L
    mem.toList().forEach {
        sum += it.second
    }

    return sum
}

fun getArrdList(mask: String, keys: List<Long>): List<Long> {
    val preMask = mask.replace('X', '0').toLong(2)

    val mapKeys = keys.map {
        it or preMask
    }

    if (!mask.contains('X')) {
        return mapKeys
    }

    val loMand = mask.replaceFirst('X', '!').replace('X', '1').replace('0', '1').replace('!', '0').toLong(2)
    val hiMor = mask.replaceFirst('X', '!').replace('X', '0').replace('!', '1').toLong(2)

    return listOf(
        getArrdList(mask.replaceFirst('X', '0'), mapKeys.map { it and loMand }),
        getArrdList(mask.replaceFirst('X', '0'), mapKeys.map { it or hiMor })
    ).flatten()
}

/**
 * Part 1 mask
 */
fun getMask(maskLine: String): Pair<Long, Long> {
    val mask = maskLine.split(" = ")[1]
    val andMask = mask.replace('X', '1').toLong(2)
    val orMask = mask.replace('X', '0').toLong(2)
    return Pair(andMask, orMask)
}
