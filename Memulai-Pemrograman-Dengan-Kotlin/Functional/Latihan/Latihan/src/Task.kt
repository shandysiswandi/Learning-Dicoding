fun main() {
    val text = "Kotlin".getFirstAndLast()

    val firstChar = text["first"]
    val lastChar = text["last"]
    println("First letter is $firstChar and $lastChar for second letter")
}

fun String.getFirstAndLast(): Map<String, Char>{
    return mapOf(
        "first" to this[0],
        "last" to this.last()
    )
}