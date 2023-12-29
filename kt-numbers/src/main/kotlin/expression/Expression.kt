package expression

import field.Field

sealed class Expression<T> {
    override fun toString() = ExpressionObject.show(this)
}

data class Lit<T>(val value: T) : Expression<T>()
data class Var<T>(val name: String) : Expression<T>()

abstract class UnaryExpression<T>(open val sub: Expression<T>) : Expression<T>()
data class Minus<T>(override val sub: Expression<T>) : UnaryExpression<T>(sub)
data class Recip<T>(override val sub: Expression<T>) : UnaryExpression<T>(sub)

abstract class BinaryExpression<T>(open val left: Expression<T>, open val right: Expression<T>) : Expression<T>()
data class Add<T>(override val left: Expression<T>, override val right: Expression<T>) :
    BinaryExpression<T>(left, right)

data class Multiply<T>(override val left: Expression<T>, override val right: Expression<T>) :
    BinaryExpression<T>(left, right)


object ExpressionObject {
    fun <T> show(expr: Expression<T>): String = when (expr) {
        is Lit -> expr.value.toString()
        is Var -> expr.name
        is Add -> "(${show(expr.left)} + ${show(expr.right)})"
        is Minus -> "(-${show(expr.sub)})"
        is Multiply -> "(${show(expr.left)} * ${show(expr.right)})"
        is Recip -> "(/ ${show(expr.sub)})"
        else -> "This element cannot be printed!"
    }

    fun <T> eval(expr: Expression<T>, data: Map<String, T>, field: Field<T>): T = when (expr) {
        is Lit -> expr.value
        is Var -> data.getValue(expr.name)
        is Add -> field.plus(eval(expr.left, data, field), eval(expr.right, data, field))
        is Minus -> field.neg(eval(expr.sub, data, field))
        is Multiply -> field.times(eval(expr.left, data, field), eval(expr.right, data, field))
        is Recip -> field.recip(eval(expr, data, field))
        else -> throw Exception("Cannot evaluate expression!")
    }

    fun <T> simplify(expr: Expression<T>, field: Field<T>): Expression<T> {
        return when (expr) {
            is Lit -> expr
            is Var -> expr
            is Add -> {
                val simplifiedLeft = simplify(expr.left, field)
                val simplifiedRight = simplify(expr.right, field)

                // a + 0 = 0 + a = a
                if (simplifiedLeft == field.zero) {
                    return simplifiedRight
                } else if (simplifiedRight == field.zero)
                    return simplifiedLeft

                // a + -a = 0
//                if (expr.left is Minus && expr.right is Var) {
//                    when (expr.left) {
//                        is Var -> {
//                            if (expr.left == expr.right) {
//                                return Lit(field.zero)
//                            } else {
//                                Add (simplifiedLeft, simplifiedRight)
//                            }
//                        }
//                        else -> expr
//                    }
//                }

                return Add(simplifiedLeft, simplifiedRight)
            }

            is Minus -> {
                when (val simplifiedSub = simplify(expr.sub, field)) {
                    is Minus -> simplifiedSub
                    else -> expr
                }
            }

            is Multiply -> {
                val simplifiedLeft = simplify(expr.left, field)
                val simplifiedRight = simplify(expr.right, field)

                if (simplifiedLeft == field.zero || simplifiedRight == field.zero) {
                    return Lit(field.zero)
                }

                if (simplifiedLeft == field.one) {
                    return simplifiedRight
                } else if (simplifiedRight == field.one) {
                    return simplifiedLeft
                }

                return Multiply(simplifiedLeft, simplifiedRight)
            }

            is Recip -> {
                when (val simplifiedSub = simplify(expr.sub, field)) {
                    is Recip -> simplifiedSub
                    else -> expr
                }
            }

            else -> throw Exception("Cannot simplify expression!")
        }
    }
}
