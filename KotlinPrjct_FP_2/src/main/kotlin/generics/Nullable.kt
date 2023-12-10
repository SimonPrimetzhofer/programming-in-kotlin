package generics

data class NotNullableBox<T : Any>(val e: T)

data class NullableBox<T : Any?>(val e: T)

fun main(args: Array<String>) {

    val nullableBox = NullableBox(null)
    println(nullableBox)
    // not allowed: NotNullableBox(null)
    println(NotNullableBox(1))

}
