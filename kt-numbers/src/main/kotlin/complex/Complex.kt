package complex

data class Complex(val real: Double, val imaginary: Double) {
    override fun toString() = "($real, $imaginary)"
}