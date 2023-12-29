package doubles

import field.Field
import kotlin.Double

object DoubleField : Field<Double> {
    override fun plus(x: Double, y: Double) = x + y
    override fun times(x: Double, y: Double) = x * y
    override fun neg(x: Double) = -x
    override fun recip(x: Double) = 1 / x
    override val zero: Double
        get() = 0.0
    override val one: Double
        get() = 1.0
}