package day6

import support.downloadInput

fun main() {
    val input = downloadInput(6)
    val ans = mutableListOf<List<Char>>()
    var sum = 0
    input.forEach {
        if (it.isEmpty()) {
            sum += ans.reduce { acc, list ->
                acc.intersect(list).toList()
            }.size
            ans.clear()
        } else {
            ans.add(it.toList())
        }
    }
    sum += ans.reduce { acc, list ->
        acc.intersect(list).toList()
    }.size
    println(sum)
}

