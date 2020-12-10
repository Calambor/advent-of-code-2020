package day10

import support.downloadInput

fun main() {
    println(solve(downloadInput(10, 2)))
    println("--------------------------")
    println(solve(downloadInput(10, 1)))
    println("--------------------------")
    println(solve(downloadInput(10)))
}

fun solve(inputR: List<String>): Any {
    val input = inputR.map { it.toInt() }.sorted().toMutableList()
    input.add(input.max()!! + 3)
    input.add(0, 0)
    val hops = mutableListOf<Int>()
    var prev = 0
    var prevIndex = 0
    var tot = 1L
    for (i in 0 until input.size) {
        val hop = input[i] - prev
        hops.add(hop)
        prev = input[i]
        if (hop == 3) {
            val combs = input.subList(prevIndex, i)
            val new = allCombinations(combs)
            tot *= new
            prevIndex = i
        }
    }
    return tot
}

fun allCombinations(input: List<Int>): Int {
    val jolt = input.first()
    val options = input.filter { it in (jolt+1)..(jolt+3) }
    return if (input.size == 1) {
        1
    } else {
        options.map {
            allCombinations(input.subList(input.indexOf(it), input.size))
        }.sum()
    }
}
