package generics

class SortedList<T : Comparable<T>>(vararg initials: T) {
    private var elems: ArrayList<T>

    init {
        elems = ArrayList(initials.size)
        for (e in elems) this.insert(e)
    }

    fun insert(e: T) {
        var i = 0
        while (i < elems.size && elems.get(i) < e) {
            i += 1
        }
        elems.add(i, e)
    }

    fun get(i: Int) = elems.get(i)

    fun isEmpty() : Boolean = elems.isEmpty()

    override fun toString(): String {
        return "SortedList${elems.toString()}"
    }
}

fun <T : Comparable<T>> sortedListOf(vararg elems: T) : SortedList<T> {
    val sortedList = SortedList<T>()
    for (e in elems) sortedList.insert(e)
    return sortedList
}


fun <T> maxChars(x: T, y: T) : Set<Char>
        where T : Comparable<T>,
              T : CharSequence {
    if (x > y) {
        return x.toSet()
    } else {
        return y.toSet()
    }
}

fun <T : Number> sum(list : List<T>) : T {
    var sum : Double = 0.0
    for (e in list) {
        sum += e.toDouble()
    }
    return sum as T
}

//fun <T> fnWithTypeCheck(list : List<*>) : List<T> {
//    for (e in list) {
//        if (e is T) {
//           // ...
//        }
//    }
//}

inline fun <reified T> filterTs(list : List<*>) : List<T> {
    val result = mutableListOf<T>()
    for (e in list) {
        if (e is T) {
            result.add(e)
        }
    }
    return result
}

fun main(args: Array<String>) {
    println("Hello World!")

    val strings = sortedListOf("Kotlin", "Java", "Scala")
    println(strings)

    // println(max(2, 7))
    // println(sum(listOf(1, 2, 3)))

    val nullableBox = NullableBox(null)
    println(nullableBox)
    // not allowed: NotNullableBox(null)
    println(NotNullableBox(1))

    val value = listOf<String>("A")

//    if (value is List<Int>) {
//        println("its a stack of strings")
//    }

    if (value is List<*>) {
        println("its a stack of something")
    }

    val intList = value as? List<Int>
        ?: throw IllegalArgumentException("list expected")

    try {
        var sum = 0
        for (i in intList) {
            sum += i
        }
    } catch (e : Exception) {
        println("Not an int in intList")
    }


    val listOfAny: List<Any> = listOf("A", 1, true, "B")
    val strings5: List<String> = filterTs<String>(listOfAny)
    println(strings5)

}
