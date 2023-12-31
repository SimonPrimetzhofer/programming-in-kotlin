package complex

import field.Field

object ComplexField : Field<Complex> {
    override fun plus(x: Complex, y: Complex) = Complex(x.real + y.real, x.imaginary + y.imaginary)

    override fun times(x: Complex, y: Complex) = Complex(
        (x.real * y.real) - (x.imaginary * y.imaginary),
        (x.real * y.imaginary) + (x.imaginary * y.real)
    )

    override fun neg(x: Complex) = Complex(-x.real, -x.imaginary)

    override fun recip(x: Complex) = Complex(
        x.real / ((x.real * x.real) + (x.imaginary * x.imaginary)),
        -x.imaginary / ((x.real * x.real) + (x.imaginary * x.imaginary))
    )

    override val zero: Complex
        get() = Complex(0.0, 0.0)
    override val one: Complex
        get() = Complex(1.1, 1.1)
}