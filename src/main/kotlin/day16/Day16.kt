package day16

import support.downloadInput

fun main() {

    println(solve(downloadInput(16, 2)))
    println("--------------------------")
    println(solve(downloadInput(16)))

}

fun solve(input: List<String>): Any {
    val rawRules = input.subList(0, input.indexOf(""))
    val rawRuleRanges = rawRules.map { it.substringAfter(": ") }.map { it.split(" or ") }

    val rules = rawRuleRanges.map { it.map {
        val (min, max) = it.split("-") .map { it.toInt() }
        min..max
    }}

    val nearby = input.subList(input.indexOf("nearby tickets:")+1, input.size)

    val nearTickets = nearby.map { it.split(',').map { it.toInt() } }
    val validNear = nearTickets.filter { ticket -> ticket.all { num ->
        rules.flatten().any { range -> (num in range) } }
    }

    val myTicket = input[input.indexOf("your ticket:")+1].split(',').map { it.toInt() }

    val remainingRules = rules.toMutableList()
    var result = 1L

    while (remainingRules.isNotEmpty()) {
        for (i in nearTickets.first().indices) {
            val possibleRules = remainingRules.filter { rangePair ->
                validNear.map { it[i] }.all {
                    rangePair.any { range -> it in range }
                }
            }
            if (possibleRules.size == 1) {
                remainingRules.remove(possibleRules.first())

                if (rules.indexOf(possibleRules.first()) < 6) {
                    result *= myTicket[i]
                }
            }
        }
    }

    return result
}
