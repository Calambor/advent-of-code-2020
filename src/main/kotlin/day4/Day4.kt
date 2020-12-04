package day4

import support.downloadInput

val reqs = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

fun main() {
    val input = downloadInput(4)
    var currPass = mutableMapOf<String, String>()
    var valid = 0
    input.forEach { row ->
        if (row.isEmpty()) {
            if (currPass.isValid()) {
                valid++
            }
            currPass = mutableMapOf<String, String>()
        } else {
            row.split(' ').forEach {
                val entry = it.split(':')
                currPass.put(entry[0], entry[1])
            }
        }
    }
    if (currPass.isValid()) {
        valid++
    }
    println(valid)
}

private fun Map<String, String>.isValid(): Boolean {
    return reqs.all { keys.contains(it) } &&
            all { (key, value) ->
                when (key) {
                    "byr" -> value.toInt() in (1920..2002)
                    "iyr" -> value.toInt() in (2010..2020)
                    "eyr" -> value.toInt() in (2020..2030)
                    "hgt" -> {
                        val h = value.substring(0, value.length - 2)
                        val measure = value.substring(value.length - 2, value.length)
                        if (measure == "cm") {
                            h.toInt() in 150..193
                        } else if (measure == "in") {
                            h.toInt() in 59..76
                        } else {
                            false
                        }
                    }
                    "hcl" -> {
                        value[0] == '#' && value.length == 7 && value.substring(1)
                            .all { it in '0'..'9' || it in 'a'..'f' }
                    }
                    "ecl" -> value in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                    "pid" -> value.length == 9 && value.all { it in '0'..'9' }
                    else -> true
                }
            }
}
