package day15

import support.downloadInput

fun main() {

    println(solve(downloadInput(15, 1)))
    println("--------------------------")
    println(solve(downloadInput(15)))

}

fun solve(raw: List<String>): Any {
    val start = raw.first().split(",").map { it.toInt() }.toMutableList()

    val mem = mutableMapOf<Int, Int>()
    start.forEachIndexed { index, i -> mem.put(i, index) }

    var last = start.last()
    for (i in start.size until 30000000) {
        val spoken2 = if (mem[last] == null) {
            0
        } else {
            i - mem[last]!!-1
        }
        mem.put(last, i-1)
        last = spoken2
    }
    return last
}
