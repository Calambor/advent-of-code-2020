package day1

import support.downloadInput
import java.io.File

fun main() {
    val input = downloadInput(1);
    File("input1").readLines().forEach {
        println(it)
    }
}