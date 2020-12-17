package day17

import support.downloadInput

fun main() {

    //println(solve(downloadInput(17, 1)))
    println("--------------------------")
    println(solve(downloadInput(17)))

}

const val duration = 6

fun solve(input: List<String>): Any {

    val worldSize = input.first().length + 2 * duration +2

    var grid: List<List<MutableList<Int>>> =
        List(worldSize) {
            List(worldSize) {
                List(4 + 2 * duration) { 0 }.toMutableList()
            }
        }
    var alt: List<List<MutableList<Int>>> =
        List(worldSize) {
            List(worldSize) {
                List(4 + 2 * duration) { 0 }.toMutableList()
            }
        }

    input.forEachIndexed { x, col ->
        col.forEachIndexed { y, active ->
            grid[x + duration+2][y + duration+2][duration+1] = if (active == '#') 1 else 0
        }
    }

    for (step in 0 until duration) {
        grid.printSlice(duration+1)
        grid.printSlice(duration)
        grid.printSlice(duration-1)
        println()
        grid.step(alt)
        val tmp = grid
        grid = alt
        alt = tmp
    }

    return grid.flatten().flatten().sum()
}

fun List<List<MutableList<Int>>>.printSlice(z: Int) {
    this.forEach { col ->
        col.forEach {
            print(
                if (it[z] == 1) {
                    "#"
                } else {
                    "."
                }
            )
        }
        println()
    }
}

fun List<List<MutableList<Int>>>.step(outGrid: List<List<MutableList<Int>>>) {

    for (x in 1 until size-1) {
        for (y in 1 until size-1) {
            for (z in 1 until 2 * duration+3) {
                outGrid[x][y][z] = nextState(x, y, z)
            }
        }
    }

}

fun List<List<MutableList<Int>>>.nextState(x: Int, y: Int, z: Int): Int {
    val near = subList(x - 1, x + 2).map {
        it.subList(y - 1, y + 2).map {
            it.subList(z - 1, z + 2)
        }
    }
    val nearCount = near.flatten().flatten().sum()

    return if (this[x][y][z] == 1) {
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

// Not 293
// Not 297
