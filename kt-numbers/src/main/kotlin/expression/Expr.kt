package expression

import field.Field

sealed class Expr<T> {
    override fun toString() = show()
}

data class Lit<T>(val value: T) : Expr<T>()
data class Var<T>(val name: String) : Expr<T>()

abstract class UnaryExpression<T>(open val sub: Expr<T>) : Expr<T>()
data class Minus<T>(override val sub: Expr<T>) : UnaryExpression<T>(sub)
data class Recip<T>(override val sub: Expr<T>) : UnaryExpression<T>(sub)

abstract class BinaryExpression<T>(open val left: Expr<T>, open val right: Expr<T>) : Expr<T>()
data class Add<T>(override val left: Expr<T>, override val right: Expr<T>) :
    BinaryExpression<T>(left, right)

data class Multiply<T>(override val left: Expr<T>, override val right: Expr<T>) :
    BinaryExpression<T>(left, right)

fun <T> Expr<T>.show(): String = when (this) {
    is Lit -> this.value.toString()
    is Var -> this.name
    is Add -> "(${left.show()} + ${right.show()})"
    is Minus -> "(-${sub.show()})"
    is Multiply -> "(${left.show()} * ${right.show()})"
    is Recip -> "(/ ${sub.show()})"
    else -> "This element cannot be printed!"
}

fun <T> Expr<T>.eval(data: Map<String, T>, field: Field<T>): T = when (this) {
    is Lit -> value
    is Var -> data.getValue(name)
    is Add -> field.plus(left.eval(data, field), right.eval(data, field))
    is Minus -> field.neg(sub.eval(data, field))
    is Multiply -> field.times(
        left.eval(data, field),
        right.eval(data, field)
    )
    is Recip -> field.recip(sub.eval(data, field))
    else -> throw Exception("Cannot evaluate expression!")
}

fun <T> Expr<T>.simplify(field: Field<T>): Expr<T> {
    return when (this) {
        is Lit -> this
        is Var -> this
        is Add -> {
            val simplifiedLeft = left.simplify(field)
            val simplifiedRight = right.simplify(field)

            // a + 0 = 0 + a = a
            if (simplifiedLeft == Lit(field.zero)) {
                return simplifiedRight
            } else if (simplifiedRight == Lit(field.zero)){
                return simplifiedLeft
            }

            return Add(simplifiedLeft, simplifiedRight)
        }
        is Minus -> {
            when (val simplifiedSub = sub.simplify(field)) {
                is Minus -> simplifiedSub.sub
                else -> simplifiedSub
            }
        }
        is Multiply -> {
            val simplifiedLeft = left.simplify(field)
            val simplifiedRight = right.simplify(field)

            if (simplifiedLeft == Lit(field.zero) || simplifiedRight == Lit(field.zero)) {
                return Lit(field.zero)
            }

            if (simplifiedLeft == Lit(field.one)) {
                return simplifiedRight
            } else if (simplifiedRight == Lit(field.one)) {
                return simplifiedLeft
            }

            return Multiply(simplifiedLeft, simplifiedRight)
        }
        is Recip -> {
            when (val simplifiedSub = sub.simplify(field)) {
                is Recip -> simplifiedSub.sub
                else -> simplifiedSub
            }
        }
        else -> throw Exception("Cannot simplify expression!")
    }
}
