package day17

import support.downloadInput

fun main() {
    println(solve(downloadInput(17, 1)))
    println("--------------------------")
    println(solve(downloadInput(17)))
}

const val duration = 6

/** Might have allocated some extra world size */
fun solve(input: List<String>): Any {

    val worldSize = input.first().length + 2 * duration + 2

    var grid: List<List<List<MutableList<Int>>>> =
        List(worldSize) {
            List(worldSize) {
                List(4 + 2 * duration) {
                    List(4 + 2 * duration) { 0 }.toMutableList()
                }
            }
        }
    var alt: List<List<List<MutableList<Int>>>> =
        List(worldSize) {
            List(worldSize) {
                List(4 + 2 * duration) {
                    List(4 + 2 * duration) { 0 }.toMutableList()
                }
            }
        }

    input.forEachIndexed { x, col ->
        col.forEachIndexed { y, active ->
            grid[x + duration + 1][y + duration + 1][duration + 1][duration + 1] = if (active == '#') 1 else 0
        }
    }

    for (step in 0 until duration) {
        //grid.printSlice(duration+1, duration+1)
        //println()

        grid.step(alt)
        val tmp = grid
        grid = alt
        alt = tmp
    }

    return grid.flatten().flatten().flatten().sum()
}

fun List<List<List<MutableList<Int>>>>.printSlice(z: Int, d: Int) {
    this.forEach { col ->
        col.forEach {
            print(
                if (it[z][d] == 1) {
                    "#"
                } else {
                    "."
                }
            )
        }
        println()
    }
}

fun List<List<List<MutableList<Int>>>>.step(outGrid: List<List<List<MutableList<Int>>>>) {
    for (x in 1 until size - 1) {
        for (y in 1 until size - 1) {
            for (z in 1 until 2 * duration + 2) {
                for (d in 1 until 2 * duration + 2) {
                    outGrid[x][y][z][d] = nextState(x, y, z, d)
                }
            }
        }
    }
}

fun List<List<List<MutableList<Int>>>>.nextState(x: Int, y: Int, z: Int, d: Int): Int {
    val near = subList(x - 1, x + 2).map {
        it.subList(y - 1, y + 2).map {
            it.subList(z - 1, z + 2).map {
                it.subList(d - 1, d + 2)
            }
        }
    }
    val nearCount = near.flatten().flatten().flatten().sum()

    return if (this[x][y][z][d] == 1) {
        if (nearCount in 3..4) { // includes itself
            1
        } else {
            0
        }
    } else {
        if (nearCount == 3) {
            1
        } else {
            0
        }
    }
}
