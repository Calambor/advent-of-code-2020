package day9

import support.downloadInput

fun main() {
    val input = downloadInput(9).map { it.toLong() }

    val s = 25
    var index = s

    var target: Long = 0
    while (index < input.size) {
        val sub = input.subList(index - s, index).map { it }
        if (sub.any { x -> sub.any { y -> x + y == input[index] && x != y } }) {

        } else {
            target = input[index]
            println("fail ${input[index]}")
        }
        index++
    }

    index = 0

    while (index < input.size) {
        var i2 = 1
        while (true) {
            val sub = input.subList(index, index + i2)
            val sum = sub.sum()
            if (sum == target) {
                println("succ ${sub.min()!! + sub.max()!!}")
            } else if (sum > target) {
                break
            }
            i2++
        }
        index++
    }
}
