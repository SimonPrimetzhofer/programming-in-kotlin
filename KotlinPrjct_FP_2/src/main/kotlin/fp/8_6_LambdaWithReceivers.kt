package fp

import java.io.FileReader


fun String.ext() {
    println(this.length)
}

fun withRcvr(s: String, lbdaWithRcvr: String.() -> Unit) {
    s.lbdaWithRcvr()
}

fun <T, R> using(t : T, fn: T.() -> R) : R {
    return t.fn()
}

fun <T> T.build(fn: T.() -> Unit) : T {
    this.fn()
    return this
}

fun main() {

    val incr: Int.(Int) -> Int = {
            other -> plus(other)
    }

    val incrBy2 =  1.incr(2)
    println(incrBy2)

    val s : String = "ABC"

    s.ext()

    withRcvr(s) {
        println(this.length)
    }

    val text =
        using(StringBuilder()) {
            append("!")
            append("Kotlin ")
            append("Hello ")
            reverse()
            toString()
        }

    val text2 =
        StringBuilder().build() {
            append("!")
            append("Kotlin ")
            append("Hello ")
            reverse()

        } .toString()

    val reader : java.io.FileReader = FileReader("text.txt")
    using(reader) {
        forEachLine {
            println(it)
        }
    }

    val l =
        mutableListOf<Int>().build {
            add(2)
            add(3)
            add(4)
            reverse()
        }
    println(l)

    val multiLine =
        with(StringBuilder()) {
            appendLine("First line")
            append("2nd ")
            append("line")
            toString()
        }

    println(multiLine)


}

