package day13

import support.downloadInput

fun main() {

    println(solve2(downloadInput(13, 3)))
    println("--------------------------")
    println(solve2(downloadInput(13)))

}

fun solvePart1(input: List<String>): Any {
    val myTime = input.first().toInt()
    val buses = input.get(1).split(",").filter { it != "x" }.map { it.toInt() }

    val best = buses.minBy {
        it - myTime % it
    }!!

    return (best - myTime % best) * best
}

fun solve2(input: List<String>): Any {
    val buses = input.get(1).split(",").map {
        if (it == "x") {
            null
        } else {
            it.toLong()
        }
    }.mapIndexed { index, l ->
        if (l != null) {
            Pair(index, l)
        } else {
            null
        }
    }.filterNotNull()

    val interesting = buses.take(5)

    var cycle = 0L
    var cycleS = 0L
    var b = 1L
    while (true) {
        val first = (interesting[0].second * b - interesting[0].first)

        if (interesting.fullVerify(first)) {
            if (cycleS != 0L) cycle = first - cycleS
            cycleS = if (cycleS == 0L) first else cycleS
            println(first)
            if (cycle != 0L) {
                break
            }
        }
        b++
    }
    println("$cycle : $cycleS")

    var a = 1L
    while (true) {
        val first = cycle * a + cycleS

        if (buses.fullVerify(first)) {
            return first
        }
        a++
        if (a % 1000000 == 0L) {
            println("try $first")
        }
    }
}

fun List<Pair<Int, Long>>.fullVerify(attempt: Long): Boolean {
    return all { (i, b) ->
        val valid = (attempt + i) % b == 0L
        valid
    }
}
