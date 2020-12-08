package day8

import support.downloadInput

fun main() {
    val f = downloadInput(8).toMutableList()

    (0 until f.size).forEach { rowIndex ->
        val input = downloadInput(8).toMutableList()
        val row = input[rowIndex]
        if (row.take(3) == "nop") {
            input[rowIndex] = "jmp" + row.substring(3)
        } else if (row.take(3) == "jmp") {
            input[rowIndex] = "nop" + row.substring(3)
        }

        var running = true
        var acc = 0
        var ptr = 0

        while (running) {
            if (ptr >= input.size) {
                println("Succ $acc")
            }
            val (op, arg) = input[ptr].split(' ')
            input[ptr] = "passed 0"

            when(op) {
                "acc" -> acc += arg.toInt()
                "jmp" -> ptr += arg.toInt()-1
                "nop" -> {}
                "passed" -> running = false
            }
            ptr++
        }
    }
}