package day5

import support.downloadInput

fun main() {

    val input = downloadInput(5)

    println(pow2(5))
    println(pow2(2))
    println(pow2(3))

    var high = 0
    var prev: Int? = null

    val exists = input.map { pass ->
        val id = pass.map { if (it == 'B' || it == 'R') 1 else 0 }.reversed().reduceIndexed { index, acc, i ->
            if (i == 0) {
                acc
            } else {
                acc + pow2(index)
            }
        }
        if (id > high) {
            high = id
        }
        println("id $id")

        id
    }.sorted()

    exists.map {
        if (prev != null && it - prev!! == 2) {
            println("Found: ${prev!! + 1}")
        }
        prev = it
    }

    println(high)

}

fun pow2(exp: Int): Int {
    var acc = 1
    (1..exp).forEach {
        acc *= 2
    }
    return acc
}
