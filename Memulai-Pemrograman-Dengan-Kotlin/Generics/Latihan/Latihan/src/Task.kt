// Coming Soon
fun main() {
    val stringResult = getResult<String, Any>("Kotlin")
    val intResult = getResult<Int, Any>(100)

    // TODO 2
    println("""
        String result: $stringResult
        Int result: $intResult
    """.trimIndent())
}

// TODO 1
inline fun <T, reified int> getResult(args: T): Int {

    return when (args) {
        is Int -> args * 5
        is String -> args.length
        else -> 0
    }
}