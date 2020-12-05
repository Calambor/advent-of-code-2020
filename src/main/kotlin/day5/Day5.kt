package day5

import support.downloadInput

fun main() {

    val input = downloadInput(5)

    var high = 0
    var prev: Int? = null

    val exists = input.map { pass ->
        val id = pass.map { if (it == 'B' || it == 'R') 1 else 0 }.reduceIndexed { index, acc, i ->
            acc.shl(1) + i
        }
        if (id > high) {
            high = id
        }
        println("id $id")

        id
    }.sorted()

    println("Highest id: $high")

    exists.map {
        if (prev != null && it - prev!! == 2) {
            println("My seat: ${prev!! + 1}")
        }
        prev = it
    }
}
