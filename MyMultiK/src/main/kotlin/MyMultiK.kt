import kotlin.math.max
import kotlin.math.min

class TDArray<T>(private val nRows: Int, private val nColumns: Int, initFn: (row: Int, col: Int) -> T) {
    // internal "array" (MutableList of MutableLists)
    // Use the following MutableList constructor:
    // MutableList(size: Int, init: (index: Int) -> T): MutableList<T>
    private val data: MutableList<MutableList<T>> =
        MutableList(nRows) { rowIndex ->
            MutableList(nColumns) { colIndex ->
                initFn(rowIndex, colIndex)
            }
        }

    // secondary constructor, just call this with appropriate values (you can assume each row has the same length)
    constructor(data: List<List<T>>) :
            this(data.size,
                data.firstOrNull()?.size ?: 0, { row, col ->
                    data[row][col]
                })

    // Nicely formatted 2D Array string
    // Just use .toString() on each value
    // For left-alignment, you can use .padStart() on strings
    override fun toString(): String {
        return buildString {
            // The "this" in this block is a StringBuilder
            // You can just call append("...") here to add stuff
            // to the final string
            data.forEach {
                it.forEach { this.append("${it.toString()} ") }
                this.append("\n")
            }
        }
    }

    // Pair of (number of rows, number of columns)
    fun dimension() = Pair(nRows, nColumns)

    // +arr should return number of rows of arr
    operator fun unaryPlus() = nRows

    // -arr should return number of columns of arr
    operator fun unaryMinus() = nColumns

    // !arr should return dimension pair of arr (e.g., (15, 10))
    operator fun not() = dimension()

    // arr[row, col] should return value stored at that index or null if index is invalid
    operator fun get(row: Int, col: Int) = if (row in 0..<+this || col in 0..<-this) data[row][col] else null

    // arr(row, col) should behave as arr[row, col]
    operator fun invoke(row: Int, col: Int) = get(row, col)

    // arr[row, col] = value should set the value at the corresponding loction if
    // the location is valid
    operator fun set(row: Int, col: Int, newValue: T) =
        if (row in 0..<+this || col in 0..<-this) data[row].set(col, newValue) else null

    // arr(row, col, value) should have as arr[row, col] = value
    operator fun invoke(row: Int, col: Int, newValue: T) = set(row, col, newValue)

    // arr[rows, cols] (for example arr[1..4, 2..3]) should return a new TDArray
    // with the content in the specified ranges (in the example the rows 1, 2, 3, 4 and
    // the columns 2, 3)
    operator fun get(rows: IntRange, cols: IntRange): TDArray<T>
        {
            // due to verbosity, i decided to introduce vals for upper and lower bounds
            val lowerBoundRow = max(rows.first, 0)
            val upperBoundRow = min(rows.last + 1, nRows)

            val lowerBoundCol = max(cols.first, 0)
            val upperBoundCol = min(cols.last + 1, nColumns)

            return TDArray(
                upperBoundRow - lowerBoundRow,
                upperBoundCol - lowerBoundCol,
                ) {
                row, col ->
                    data[lowerBoundRow + row][lowerBoundCol + col]
            }
        }

    // arr(rows, cols) should behave as arr[rows, cols]
    operator fun invoke(rows: IntRange, cols: IntRange) = get(rows, cols)

    // arr + str should call .toString() on every element in the array, append str to it and return a new array
    operator fun plus(str: String) =
        TDArray(
            nRows,
            nColumns
        ) { row, col -> get(row, col).toString() + str }

    // x in arr should check if x is contained in any of the array's cells
    operator fun contains(x: T) =
        data.flatten().contains(x)

    // for (x in arr) should be possible, thus iterator() must be implemented
    // use an anonymous object for that
    operator fun iterator(): Iterator<T> = object {
        val iterator = data.flatten().iterator()
    }.iterator // i would have just returned data.flatten().iterator but an anonymous object is required
}

// for TDArrays of Comparables we want an extension function to derive the minimum
// If the array is empty, returns null
fun <T : Comparable<T>> TDArray<T>.min(): T? {
    var min = this[0, 0] ?: return null

    for (elem in this) {
        if (elem < min)
            min = elem
    }

    return min
}

// for TDArrays of Comparables we want an extension function to derive the maximum
// If the array is empty, returns null
fun <T : Comparable<T>> TDArray<T>.max(): T? {
    var max = this[0, 0] ?: return null

    for (elem in this) {
        if (elem > max)
            max = elem
    }

    return max
}

fun main() {
    val arr: TDArray<String> = TDArray(15, 11) { r, c -> "${r}|${c}" }
    /*
    0|0 0|1 0|2 0|3 0|4 0|5 0|6 0|7 0|8 0|9 0|10
    1|0 1|1 1|2 1|3 1|4 1|5 1|6 1|7 1|8 1|9 1|10
    2|0 2|1 2|2 2|3 2|4 2|5 2|6 2|7 2|8 2|9 2|10
    3|0 3|1 3|2 3|3 3|4 3|5 3|6 3|7 3|8 3|9 3|10
    4|0 4|1 4|2 4|3 4|4 4|5 4|6 4|7 4|8 4|9 4|10
    5|0 5|1 5|2 5|3 5|4 5|5 5|6 5|7 5|8 5|9 5|10
    6|0 6|1 6|2 6|3 6|4 6|5 6|6 6|7 6|8 6|9 6|10
    7|0 7|1 7|2 7|3 7|4 7|5 7|6 7|7 7|8 7|9 7|10
    8|0 8|1 8|2 8|3 8|4 8|5 8|6 8|7 8|8 8|9 8|10
    9|0 9|1 9|2 9|3 9|4 9|5 9|6 9|7 9|8 9|9 9|10
    10|0 10|1 10|2 10|3 10|4 10|5 10|6 10|7 10|8 10|9 10|10
    11|0 11|1 11|2 11|3 11|4 11|5 11|6 11|7 11|8 11|9 11|10
    12|0 12|1 12|2 12|3 12|4 12|5 12|6 12|7 12|8 12|9 12|10
    13|0 13|1 13|2 13|3 13|4 13|5 13|6 13|7 13|8 13|9 13|10
    14|0 14|1 14|2 14|3 14|4 14|5 14|6 14|7 14|8 14|9 14|10
    */
    println(arr)
    println()
    // (15, 11)
    println(arr.dimension())
    println()
    // (15, 11)
    println(!arr)
    println()
    // 15
    println((!arr).first)
    println()
    // 11
    println((!arr).second)
    println()
    // 2|2
    println(arr[2, 2])
    println()
    /*
    1|3 1|4
    2|3 2|4
    3|3 3|4
    4|3 4|4
    */
    println(arr[1..4, 3..4])
    println()
    /*
    6|8 6|9 6|10
    7|8 7|9 7|10
    8|8 8|9 8|10
    9|8 9|9 9|10
    10|8 10|9 10|10
    11|8 11|9 11|10
    12|8 12|9 12|10
    13|8 13|9 13|10
    14|8 14|9 14|10
    */
    println(arr[6..100, 8..100])
    println()

    /* custom test case
    0|0 0|1 0|2 0|3 0|4 0|5 0|6 0|7 0|8
    1|0 1|1 1|2 1|3 1|4 1|5 1|6 1|7 1|8
    2|0 2|1 2|2 2|3 2|4 2|5 2|6 2|7 2|8
    3|0 3|1 3|2 3|3 3|4 3|5 3|6 3|7 3|8
    4|0 4|1 4|2 4|3 4|4 4|5 4|6 4|7 4|8
    5|0 5|1 5|2 5|3 5|4 5|5 5|6 5|7 5|8
    6|0 6|1 6|2 6|3 6|4 6|5 6|6 6|7 6|8
     */
    println(arr[-100..6, -100..8])
    println()

    /*
    0|0post 0|1post 0|2post 0|3post 0|4post 0|5post 0|6post 0|7post 0|8post 0|9post 0|10post
    1|0post 1|1post 1|2post 1|3post 1|4post 1|5post 1|6post 1|7post 1|8post 1|9post 1|10post
    2|0post 2|1post 2|2post 2|3post 2|4post 2|5post 2|6post 2|7post 2|8post 2|9post 2|10post
    3|0post 3|1post 3|2post 3|3post 3|4post 3|5post 3|6post 3|7post 3|8post 3|9post 3|10post
    4|0post 4|1post 4|2post 4|3post 4|4post 4|5post 4|6post 4|7post 4|8post 4|9post 4|10post
    5|0post 5|1post 5|2post 5|3post 5|4post 5|5post 5|6post 5|7post 5|8post 5|9post 5|10post
    6|0post 6|1post 6|2post 6|3post 6|4post 6|5post 6|6post 6|7post 6|8post 6|9post 6|10post
    7|0post 7|1post 7|2post 7|3post 7|4post 7|5post 7|6post 7|7post 7|8post 7|9post 7|10post
    8|0post 8|1post 8|2post 8|3post 8|4post 8|5post 8|6post 8|7post 8|8post 8|9post 8|10post
    9|0post 9|1post 9|2post 9|3post 9|4post 9|5post 9|6post 9|7post 9|8post 9|9post 9|10post
    10|0post 10|1post 10|2post 10|3post 10|4post 10|5post 10|6post 10|7post 10|8post 10|9post 10|10post
    11|0post 11|1post 11|2post 11|3post 11|4post 11|5post 11|6post 11|7post 11|8post 11|9post 11|10post
    12|0post 12|1post 12|2post 12|3post 12|4post 12|5post 12|6post 12|7post 12|8post 12|9post 12|10post
     13|0post 13|1post 13|2post 13|3post 13|4post 13|5post 13|6post 13|7post 13|8post 13|9post 13|10post
     14|0post 14|1post 14|2post 14|3post 14|4post 14|5post 14|6post 14|7post 14|8post 14|9post 14|10post
     */

    println(arr + "post")
    println()
    // true
    println("2|2" in arr)
    println()
    // false
    println("programming|kotlin" in arr)
    println()
    // 0|0
    println(arr.min())
    println()
    // 9|9
    println(arr.max())
    println()
    arr[0, 0] = "# (ASCII 43)"
    arr(0, 1, "~ (ASCII 176)")
    // # (ASCII 43)
    println(arr.min())
    println()
    // ~ (ASCII 176)
    println(arr.max())
    println()
}