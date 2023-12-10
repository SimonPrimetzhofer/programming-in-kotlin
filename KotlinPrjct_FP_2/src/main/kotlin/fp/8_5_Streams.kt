package fp

import java.util.stream.Collectors

fun main() {


    val list = listOf<Int>(1, 3, 5, 7, 2, 5, 9, 13, 1, 3, 5, 7, 2, 5, 9, 13)

    val result : List<Int> =
        list.stream()
            .map { it * it }
            .filter { it > 10 }
            .limit(2)
            .collect(Collectors.toList())
    println(result)

    val resultParallel : List<Int> =
        list.parallelStream()
            .map { it * it }
            .filter { it > 10 }
            .collect(Collectors.toList())
    println(resultParallel)

}