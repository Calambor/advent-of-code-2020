package day1

import support.downloadInput
import support.toInts1

fun main() {
    val input = downloadInput(1).toInts1()
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
