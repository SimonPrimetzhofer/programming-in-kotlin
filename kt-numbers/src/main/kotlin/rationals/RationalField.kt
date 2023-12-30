package rationals

import field.Field
import kotlin.math.absoluteValue

object RationalField : Field<Rational> {
    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

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
            x.numerator * y.numerator / minDenominator,
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