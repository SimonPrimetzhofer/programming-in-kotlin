package rationals

import field.Field
import kotlin.math.absoluteValue

data class Rational(val numerator: Int, val denominator: Int) {
    override fun toString() = "$numerator / $denominator"
}

object RationalObject {
    fun apply(numerator: Int, denominator: Int) =
        if (numerator == 0) {
            Rational(0, 1)
        } else {
            val g = gcd(numerator.absoluteValue, denominator.absoluteValue)
            if (numerator <= 0 && denominator < 0) {
                Rational(-numerator / g, -denominator / g)
            } else {
                Rational(numerator / g, denominator / g)
            }
        }

    fun apply(numerator: Int) = Rational(numerator, 1)
    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

    object rationalField : Field<Rational> {
        override fun plus(x: Rational, y: Rational): Rational {
            val minDenominator = gcd(x.denominator, y.denominator).absoluteValue

            return Rational(
                ((x.numerator * y.denominator) + (y.numerator * x.denominator)) / minDenominator,
                x.denominator * y.denominator / minDenominator
            )
        }

        override fun times(x: Rational, y: Rational): Rational {
            val minDenominator = gcd(x.denominator, y.denominator).absoluteValue

            return Rational(
                x.numerator * y.denominator / minDenominator,
                x.denominator * y.denominator / minDenominator
            )
        }

        override fun neg(x: Rational): Rational {
            val reduceFactor = gcd(x.numerator, x.denominator).absoluteValue
            return Rational(-x.numerator / reduceFactor, x.denominator / reduceFactor)
        }

        override fun recip(x: Rational): Rational {
            val reduceFactor = gcd(x.numerator, x.denominator).absoluteValue
            return Rational(x.denominator / reduceFactor, x.numerator / reduceFactor)
        }

        override val zero: Rational
            get() = Rational(0, 1)
        override val one: Rational
            get() = Rational(1, 1)
    }
}