package day2

import support.downloadInput

fun main() {
    val input = downloadInput(2).map { it.split(' ') }
    var validNum = 0
    input.forEach { a ->
        val min = a.first().split('-')[0].toInt()
        val max = a.first().split('-')[1].toInt()
        val char = a[1][0]

        val pw = a[2]
        val count = pw.count { it == char }
        if (count in min..max) {
            //validNum++ // Part 1
        }
        if ((pw[min - 1] == char).xor(pw[max - 1] == char)) {
            validNum++
        }
    }
    println(validNum)
}
