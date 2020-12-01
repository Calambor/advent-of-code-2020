package day1

import support.downloadInput
import support.toInts1

fun main() {
    val day1Data = downloadInput(1).toInts1()
    val subtracted = day1Data.map { row -> 2020 - row }
    val result = day1Data.first { a -> subtracted.contains(a) }

    val otherResult = 2020-result

    println(result * otherResult)
}