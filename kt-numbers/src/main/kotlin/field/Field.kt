package field

interface Field<T> {
    fun plus(x: T, y: T): T
    fun times(x: T, y: T): T
    fun neg(x: T): T
    fun recip(x: T): T
    val zero: T
    val one: T
}

