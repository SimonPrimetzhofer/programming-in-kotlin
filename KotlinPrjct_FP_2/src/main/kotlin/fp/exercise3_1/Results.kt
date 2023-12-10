package fp.exercise3_1

import java.nio.file.Files
import java.nio.file.Paths

data class Results(val id: Int, val name: String, val points: List<Int>)

enum class Grade {
    EXCELLENT, GOOD, SATISFACTORY, SUFFICIENT, INSUFFICIENT
}

fun main(args: Array<String>) {

    val lines: List<String> = Files.readAllLines(Paths.get("files/results.csv"))

    // Task 1: List of Results objects
    val resultList: List<Results> = lines
        .drop(1)
        .map { it.split(",").map { it.trim() } }
        .map { it ->
            Results(
                it[0].toInt(),
                it[1],
                it.drop(2).map { it.toInt() }
            )
        }
    println("resultList = $resultList \n")

    // Task 2: Number of solved tasks

    val nSolvedPerStnd: Map<String, Int> = resultList
        .associateBy({ it.name }, { it.points.count { it >= 3 } })
    println("nSolvedPerStnd = $nSolvedPerStnd \n")

    // Task 3: Sufficient tasks solved

    val partitionedLists = resultList.partition { (nSolvedPerStnd[it.name] ?: 0) >= 8 }
    val (suff, notSuff) = Pair(partitionedLists.first.map { it.name }, partitionedLists.second.map { it.name })
    println("suffientSolved = $suff, not sufficient solved = $notSuff \n")

    // Task 4: Grading

    val grades: Map<String, Grade> = resultList.associateBy({ it.name }, { computeGrade(it.points) })
    println("grades = $grades \n")

    // Task 5: Grade statistics

    val nStudentsWithGrade: Map<Grade, Int> = grades.values.groupingBy { it }.eachCount()
    println("nStudentsWithGrade = $nStudentsWithGrade \n")

    // Task 6: Number solved per assignment

    val nSolvedPerAssnmt: List<Pair<Int, Int>> =
        (1..(resultList.maxOfOrNull { it.points.size } ?: 0))
            .map { index ->
                Pair(index,
                    resultList.map { it.points[index - 1] }.count { it >= 3 })
            }

    println("nSolvedPerAssnmt = $nSolvedPerAssnmt \n")

}

fun computeGrade(points: List<Int>): Grade {
    val grade = if (points.count { p -> p >= 3 } < 8) Grade.INSUFFICIENT
    else {
        val avrg = points.sorted().reversed().drop(2).sum() / 8.0
        if (avrg < 5.0) Grade.INSUFFICIENT
        else if (avrg < 6.5) Grade.SUFFICIENT
        else if (avrg < 8.0) Grade.SATISFACTORY
        else if (avrg < 9.0) Grade.GOOD
        else Grade.EXCELLENT
    }
    return grade
}