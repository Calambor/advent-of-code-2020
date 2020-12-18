package day18

import support.downloadInput

fun main() {

    //println(solve(downloadInput(18, 1)))
    println("--------------------------")
    println(solve(downloadInput(18)))

}

fun solve(input: List<String>): Any {
    var acc = 0L
    input.map {
        var math = it
        while (math.contains('(')) {
            math = replaceParantheses(math)
        }
        doBasicMath(math)
    }.forEach {
        acc += it.toLong()
    }

    return acc
}

fun replaceParantheses(math: String): String {

    math.forEachIndexed { index, char ->
        if (char == '(') {
            val rest = math.substring(index + 1)
            if (rest.indexOf(')') < rest.indexOf('(') || rest.indexOf('(') == -1) {
                val end = math.indexOf(')', index + 1)
                return math.replaceRange(index..end, replaceParantheses(math.substring((index + 1)..(end - 1))))
            }
        }
    }
    return doBasicMath(math)
}

fun doBasicMath(math: String): String {
    val expr = math.split(" ").toMutableList()
    var acc = 0L
    var op = "+"

    while (expr.contains("+")) {
        val index = expr.indexOf("+")
        expr.removeAt(index)
        val num = expr.removeAt(index - 1).toLong() + expr.removeAt(index - 1).toLong()
        expr.add(index - 1, num.toString())
    }

    expr.forEach {
        if (it == "+" || it == "*") {
            op = it
        } else {
            val num = it.toLong()
            when (op) {
                "+" -> acc += num
                "*" -> acc *= num
            }
        }
    }
    return acc.toString()

}


