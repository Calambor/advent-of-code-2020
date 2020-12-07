package day7

import support.downloadInput

fun main() {
    val input = downloadInput(7)

    val rules = input.map {
        val (rawKey, value) = it.split(" contain ")
        val key = rawKey.split(" ").take(2).joinToString(" ")

        val colors = value.split(", ").map {
            if (it == "no other bags.") {
                null
            } else {
                val num = it.take(1).toInt()
                val col = it.substring(2).split(" ").take(2).joinToString(" ")
                Pair(num, col)
            }
        }.filterNotNull()

        Pair(key, colors)
    }

    var tot = 0
    var added = mutableMapOf("shiny gold" to 1)
    while (added.isNotEmpty()) {
        val allNew = mutableMapOf<String, Int>()

        added.forEach { (color, num) ->
            val new = rules.first { it.first == color }.second.map { (rNum, rCol) -> Pair(rNum * num, rCol) }
            new.forEach { (rNum, rCol) ->
                tot += rNum
                if (allNew.containsKey(rCol)) {
                    allNew[rCol] = allNew[rCol]!! + rNum
                }
                else {
                    allNew[rCol] = rNum
                }
            }
        }
        added = allNew
    }
    println("Fin: $tot")
}