fun main() {
    for (number in 1.rangeTo(100)) {
        if (number%2 == 0) continue
        if (number > 15) break
        println("range result is ${number * (number + 10)}")
    }
}