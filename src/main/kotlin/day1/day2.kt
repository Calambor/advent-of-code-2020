package day1

import support.downloadInput

fun main() {
    val input = downloadInput(1).map { it.toInt() }
    input.forEach { a ->
        input.forEach { b ->
            input.forEach { c ->
                if (a + b + c == 2020) {
                    println(a * b * c)
                }
            }
        }
    }

}
