package support


public fun List<String>.toInts1(): List<Int> {
    return map { it.toInt() }
}

public fun List<String>.toInts2(separator: Char = ','): List<Pair<Int, Int>> {
    return map {
        val values = it.split(separator).map {
            it.toInt()
        }
        Pair(values[0], values[1])
    }
}
