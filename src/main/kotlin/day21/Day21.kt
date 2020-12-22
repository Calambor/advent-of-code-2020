package day21

import support.downloadInput

fun main() {

    println(solve(downloadInput(21, 1)))
    println("--------------------------")
    println(solve(downloadInput(21)))

}

fun solve(input: List<String>): Any {
    val dishes = input.map { it.split(" (").first().split(" ") }
    val allergensOfDishes = input.map {
        it.split(" (")[1].run {
            substring(9, length - 1)
        }.split(", ")
    }

    val allergens = mutableMapOf<String, List<String>>()

    dishes.indices.forEach { index ->
        allergensOfDishes[index].forEach { allergen ->
            val sus = allergens[allergen]
            val newSus = if (sus == null) {
                dishes[index]
            } else {
                sus.intersect(dishes[index]).toList()
            }
            allergens.put(allergen, newSus)
        }
    }

    val uniqueAllergens = mutableMapOf<String, String>()
    while (uniqueAllergens.size < allergens.size) {
        val unique = allergens.entries.find { it.value.size == 1 }
        val ingredient = unique!!.value.first()
        uniqueAllergens.put(unique.key, ingredient)
        allergens.forEach { key, value ->
            allergens.put(key, value - ingredient)
        }
    }

    val dangerous = uniqueAllergens.keys.sorted().map { uniqueAllergens[it] }.joinToString(",")

    // part1
    // val safe = dishes.flatten().distinct().minus(allergens.values.flatten().distinct())
    // return dishes.flatten().count { it in safe }

    return dangerous
}
