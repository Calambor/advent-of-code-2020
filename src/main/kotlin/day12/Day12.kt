package day12

import support.downloadInput
import kotlin.math.abs

fun main() {

    println(solve(downloadInput(12, 1)))
    println("--------------------------")
    println(solve(downloadInput(12)))

}

enum class Dir {
    N, E, S, W
}

fun solve(input: List<String>): Any {
    var x = 0L
    var y = 0L
    var wx = 10L
    var wy = 1L

    input.forEach {
        val comm = it.first().toString()
        val arg = it.substring(1).toInt()
        when (comm) {
            Dir.N.name -> wy += arg
            Dir.E.name -> wx += arg
            Dir.S.name -> wy -= arg
            Dir.W.name -> wx -= arg
            "F" -> {
                x += wx * arg
                y += wy * arg
            }
            "R" -> {
                val (a, b) = turn(wx, wy, arg / 90)
                wx = a
                wy = b
            }
            "L" -> {
                val (a, b) = turn(wx, wy, -arg / 90 + 4)
                wx = a
                wy = b
            }
        }
    }

    return abs(x) + abs(y)
}

fun turn(x: Long, y: Long, rotationsRight: Int): Pair<Long, Long> {
    return when (rotationsRight) {
        1 -> Pair(y, -x)
        2 -> Pair(-x, -y)
        3 -> Pair(-y, x)
        else -> TODO()
    }
}
