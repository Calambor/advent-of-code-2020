package day11

import support.downloadInput

fun main() {

    println("AnsTest: "+solve(downloadInput(11, 1)))
    println("--------------------------")
    println("Ans: "+solve(downloadInput(11)))

}

fun solve(inputR: List<String>): Any {
    val input = inputR.map { it.toCharArray().toList() }
    var thisState = input
    var lastState = emptyList<List<Char>>()

    while (!thisState.equals(lastState)) {
        lastState = thisState
        thisState = lastState.mapIndexed { x, row ->
            row.mapIndexed { y, col ->
                if (col != '.') {
                    val adj = lastState.countSeenOcc(x, y)
                    if (col == 'L' && adj == 0){
                        '#'
                    } else if (col == '#' && adj >= 5) {
                        'L'
                    } else {
                        col
                    }

                } else {
                    '.'
                }
            }
        }
        //thisState.forEach { println(it) }
        //println()
    }

    return thisState.flatten().count { it == '#' }
}

fun List<List<Char>>.countSeenOcc(x: Int, y: Int): Int {
    var adj = 0
    (x-1..x+1).forEach { xl ->
        (y-1..y+1).forEach { yl ->
            (1 until size).find { dist ->
                val cx = (x - xl) * dist + x
                val cy = (y - yl) * dist + y
                if (!(x == cx && y == cy) && cx in (0 until size) && cy in (0 until first().size) && (get(cx).get(cy) == '#' || get(cx).get(cy) == 'L')) {
                    if(get(cx).get(cy) == '#') {
                        adj++
                    }
                    true
                } else {
                    false
                }
            }
        }
    }
    return adj
}


