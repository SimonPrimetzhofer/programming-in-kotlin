package fp

import java.util.*
import java.util.stream.Collectors

fun main() {

    val numbers = listOf<Int>(1, 3, 5, 7, 2, 5, 9, 13, 1, 3, 5, 7, 2, 5, 9, 13)

    numbers.asSequence().map { it * it } .filter { it > 10 } .take(2) .toList()

    val RAND : Random = Random()

    val seq : Sequence<Int> = sequenceOf(1, 2, 3)

    val infinite : Sequence<Int> = generateSequence (0) { x -> x + 1 }

    infinite.takeWhile { it < 100 } .forEach { println(it) }


    val result : List<Int> = numbers.stream().map { it * it } .filter { it > 10 } .limit(2).collect(Collectors.toList())


    seq.map { it }

    // eager
    val twoOddSquares : List<Int> =
        numbers.map {i -> i * i }
               .filter { sq -> sq % 2 != 0 }
               .take(2)

    // lazy
    val twoOddSquares2 : List<Int> =
        numbers.asSequence()
            .map {i -> i * i }
            .filter { sq -> sq % 2 != 0 }
            .take(2)
            .toList()


}