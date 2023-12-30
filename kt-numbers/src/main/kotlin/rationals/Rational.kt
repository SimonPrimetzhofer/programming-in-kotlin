package rationals

data class Rational(val numerator: Int, val denominator: Int) {
    override fun toString() = "$numerator / $denominator"
}