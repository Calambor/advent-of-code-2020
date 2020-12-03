package day3

import support.downloadInput

fun main() {
    val input = downloadInput(3)

    val slopes = listOf(
        Pair(1, 1),
        Pair(3, 1),
        Pair(5, 1),
        Pair(7, 1),
        Pair(1, 2)
    )
    val res = slopes.map { (vx, vy) ->
        var trees = 0L
        var x = 0
        var y = 0

        while (y < input.size - 1) {
            y += vy
            x = (x + vx) % input.first().length
            if (input[y][x] == '#') {
                trees++
            }
        }
        trees
    }.reduce { acc, l -> acc * l }
    println(res)
}
