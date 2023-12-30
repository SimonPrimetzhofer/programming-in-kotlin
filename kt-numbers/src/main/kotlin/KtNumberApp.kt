import expression.*
import field.Field
import integers.IntegerField

fun main() {
    // Integers
    val data = mapOf(Pair("n", 3), Pair("m", 4))

    val negateExpr = Minus(Lit(2))
    val negateVarExpr = Minus<Int>(Var("n"))

    val simpleAddExpr = Add(Lit(69), Lit(420))
    val mediumAddExpr = Add<Int>(Minus(Var("n")), Var("m"))
    val complexAddExpr = Add(Add(Var("n"), Lit(100)), Minus(Var("m")))

    val simpleMultiplyExpr = Multiply(Lit(12), Lit(5))
    val mediumMultiplyexpr = Multiply<Int>(Var("n"), Minus(Var("m")))
    val complexMultiplyexpr = Multiply(Add(Var("n"), Var("m")), Multiply(Minus(Var("m")), Lit(-4)))

    println("Integer Expressions:")
    println("-------------------------------------------------------------------------------------------------")
    printAndEvalExpression(negateExpr, data, IntegerField)
    printAndEvalExpression(negateVarExpr, data, IntegerField)
    printAndEvalExpression(simpleAddExpr, data, IntegerField)
    printAndEvalExpression(mediumAddExpr, data, IntegerField)
    printAndEvalExpression(complexAddExpr, data, IntegerField)
    printAndEvalExpression(simpleMultiplyExpr, data, IntegerField)
    printAndEvalExpression(mediumMultiplyexpr, data, IntegerField)
    printAndEvalExpression(complexMultiplyexpr, data, IntegerField)
    println("-------------------------------------------------------------------------------------------------")

    // Rationals

    // Doubles

    // Complex
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