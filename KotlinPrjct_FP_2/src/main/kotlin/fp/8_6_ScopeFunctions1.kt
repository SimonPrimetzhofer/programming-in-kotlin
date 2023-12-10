package fp

fun main() {

    // let

    val hello1 = StringBuilder().let {
        it.append("Hello")
        it.append(" Kotlin!")
        it.toString()
    }

    println(hello1)

    // with

    val hello2 = with(StringBuilder()) {
        append("Hello")
        append(" Kotlin!")
        toString()
    }

    println(hello2)

    // apply

    val hello3 = StringBuilder().apply {
        append("Hello")
        append(" Kotlin!")
    } . toString()

    println(hello3)
}