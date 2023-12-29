package integers

import field.Field

object IntegerField : Field<Int> {
    override fun plus(x: Int, y: Int) = x + y
    override fun times(x: Int, y: Int) = x * y
    override fun neg(x: Int) = -x
    override fun recip(x: Int) = 1 / x
    override val zero: Int
        get() = 0
    override val one: Int
        get() = 1
}