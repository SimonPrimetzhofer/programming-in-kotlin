import complex.Complex
import complex.ComplexField
import doubles.DoubleField
import expression.*
import field.Field
import integers.IntegerField
import rationals.Rational
import rationals.RationalField

fun main() {
    // Integers
    val intData = mapOf(Pair("n", 3), Pair("m", 4))

    val intNegateExpr = Minus(Lit(2))
    val intNegateVarExpr = Minus<Int>(Var("n"))

    val intSimpleAddExpr = Add(Lit(69), Lit(420))
    val intMediumAddExpr = Add<Int>(Minus(Var("n")), Var("m"))
    val intComplexAddExpr = Add(Add(Var("n"), Lit(100)), Minus(Var("m")))

    val intSimpleMultiplyExpr = Multiply(Lit(12), Lit(5))
    val intMediumMultiplyexpr = Multiply<Int>(Var("n"), Minus(Var("m")))
    val intComplexMultiplyexpr = Multiply(Add(Var("n"), Var("m")), Multiply(Minus(Var("m")), Lit(-4)))

    println("Integer Expressions:")
    println("-------------------------------------------------------------------------------------------------")
    printAndEvalExpression(intNegateExpr, intData, IntegerField)
    printAndEvalExpression(intNegateVarExpr, intData, IntegerField)
    printAndEvalExpression(intSimpleAddExpr, intData, IntegerField)
    printAndEvalExpression(intMediumAddExpr, intData, IntegerField)
    printAndEvalExpression(intComplexAddExpr, intData, IntegerField)
    printAndEvalExpression(intSimpleMultiplyExpr, intData, IntegerField)
    printAndEvalExpression(intMediumMultiplyexpr, intData, IntegerField)
    printAndEvalExpression(intComplexMultiplyexpr, intData, IntegerField)
    println("-------------------------------------------------------------------------------------------------")

    // Rationals
    val ratData = mapOf(Pair("n", Rational(3, 4)), Pair("m", Rational(1, 2)))

    val ratNegateExpr = Minus(Lit(Rational(6, 8)))
    val ratNegateVarExpr = Minus<Rational>(Var("n"))

    val ratSimpleAddExpr = Add(Lit(Rational(6, 9)), Lit(Rational(4, 20)))
    val ratMediumAddExpr = Add<Rational>(Minus(Var("n")), Var("m"))
    val ratComplexAddExpr = Add(Add(Var("n"), Lit(Rational(1, 1))), Minus(Var("m")))

    val ratSimpleMultiplyExpr = Multiply(Lit(Rational(1, 2)), Lit(Rational(1, 5)))
    val ratMediumMultiplyexpr = Multiply<Rational>(Var("n"), Minus(Var("m")))
    val ratComplexMultiplyexpr = Multiply(Add(Var("n"), Var("m")), Multiply(Minus(Var("m")), Lit(Rational(-4, 2))))

    println("\nRational Expressions:")
    println("-------------------------------------------------------------------------------------------------")
    printAndEvalExpression(ratNegateExpr, ratData, RationalField)
    printAndEvalExpression(ratNegateVarExpr, ratData, RationalField)
    printAndEvalExpression(ratSimpleAddExpr, ratData, RationalField)
    printAndEvalExpression(ratMediumAddExpr, ratData, RationalField)
    printAndEvalExpression(ratComplexAddExpr, ratData, RationalField)
    printAndEvalExpression(ratSimpleMultiplyExpr, ratData, RationalField)
    printAndEvalExpression(ratMediumMultiplyexpr, ratData, RationalField)
    printAndEvalExpression(ratComplexMultiplyexpr, ratData, RationalField)
    println("-------------------------------------------------------------------------------------------------")

    // Doubles
    val doubleData = mapOf(Pair("n", 3.3), Pair("m", 4.4))

    val doubleNegateExpr = Minus(Lit(2.0))
    val doubleNegateVarExpr = Minus<Double>(Var("n"))

    val doubleSimpleAddExpr = Add(Lit(6.9), Lit(4.2))
    val doubleMediumAddExpr = Add<Double>(Minus(Var("n")), Var("m"))
    val doubleComplexAddExpr = Add(Add(Var("n"), Lit(10.0)), Minus(Var("m")))

    val doubleSimpleMultiplyExpr = Multiply(Lit(1.2), Lit(5.0))
    val doubleMediumMultiplyexpr = Multiply<Double>(Var("n"), Minus(Var("m")))
    val doubleComplexMultiplyexpr = Multiply(Add(Var("n"), Var("m")), Multiply(Minus(Var("m")), Lit(-4.2)))

    println("\nDouble Expressions:")
    println("-------------------------------------------------------------------------------------------------")
    printAndEvalExpression(doubleNegateExpr, doubleData, DoubleField)
    printAndEvalExpression(doubleNegateVarExpr, doubleData, DoubleField)
    printAndEvalExpression(doubleSimpleAddExpr, doubleData, DoubleField)
    printAndEvalExpression(doubleMediumAddExpr, doubleData, DoubleField)
    printAndEvalExpression(doubleComplexAddExpr, doubleData, DoubleField)
    printAndEvalExpression(doubleSimpleMultiplyExpr, doubleData, DoubleField)
    printAndEvalExpression(doubleMediumMultiplyexpr, doubleData, DoubleField)
    printAndEvalExpression(doubleComplexMultiplyexpr, doubleData, DoubleField)
    println("-------------------------------------------------------------------------------------------------")

    // Complex
    val complexData = mapOf(Pair("n", Complex(3.0, 2.5)), Pair("m", Complex(4.0, -1.0)))

    val complexNegateExpr = Minus(Lit(Complex(2.0, 1.0)))
    val complexNegateVarExpr = Minus<Complex>(Var("n"))

    val complexSimpleAddExpr = Add(Lit(Complex(6.0, 9.0)), Lit(Complex(4.0, 20.0)))
    val complexMediumAddExpr = Add<Complex>(Minus(Var("n")), Var("m"))
    val complexComplexAddExpr = Add(Add(Var("n"), Lit(Complex(1.0, 0.5))), Minus(Var("m")))

    val complexSimpleMultiplyExpr = Multiply(Lit(Complex(1.2, 2.1)), Lit(Complex(5.0, 0.0)))
    val complexMediumMultiplyexpr = Multiply<Complex>(Var("n"), Minus(Var("m")))
    val complexComplexMultiplyexpr = Multiply(Add(Var("n"), Var("m")), Multiply(Minus(Var("m")), Lit(Complex(-4.0, -5.0))))

    println("\nComplex Expressions:")
    println("-------------------------------------------------------------------------------------------------")
    printAndEvalExpression(complexNegateExpr, complexData, ComplexField)
    printAndEvalExpression(complexNegateVarExpr, complexData, ComplexField)
    printAndEvalExpression(complexSimpleAddExpr, complexData, ComplexField)
    printAndEvalExpression(complexMediumAddExpr, complexData, ComplexField)
    printAndEvalExpression(complexComplexAddExpr, complexData, ComplexField)
    printAndEvalExpression(complexSimpleMultiplyExpr, complexData, ComplexField)
    printAndEvalExpression(complexMediumMultiplyexpr, complexData, ComplexField)
    printAndEvalExpression(complexComplexMultiplyexpr, complexData, ComplexField)
    println("-------------------------------------------------------------------------------------------------")
}

private fun <T> printAndEvalExpression(expr: Expr<T>, data: Map<String, T>, field: Field<T>) {
    val simplifiedExpr = expr.simplify(field)
    println(
        "Initial Expression: ${expr.show()}\tEvaluated Expression: ${
            expr.eval(
                data,
                field
            )
        }\tSimplified Expression: ${simplifiedExpr.show()}\tEvaluated Simplified Expression: ${simplifiedExpr.eval(data, field)}"
    )
}