package fp

fun main() {

    // mapping ------------------------------------------------------------------------------------

    //    map - map elements and create result collections
    val names = persons.map { "${it.firstName} ${it.lastName}" }
    println("names = $names")

    //    mapTo - map elements and add them to mutable collection
    val ages = mutableListOf<Int>()
    men.mapTo(ages) {
        it.age
    }
    women.mapTo(ages) {
        it.age
    }
    ages.sort()
    println("ages = $ages")

    // mapIndexed - map together with position
    val ordered =
        persons
            .sortedByDescending { it.age }
            .mapIndexed { i, p -> "${i}: ${p.toString()}" }
    println("ordered = $ordered")

    //    zip - combine elements of two collections to collection of Pairs
    val pairs =
        men.zip(women)
            .map { (m, w) -> "${m.firstName} <-> ${w.firstName}"}
    println("pairs = $pairs")

    //    associate - creates a map from collection of keys and value
    val map1: Map<String, Int> =
        persons.associate { p -> Pair("${p.firstName} ${p.lastName}", p.age) }
    println("map1 = $map1")

    // associateBy
    val map2 =
        persons.associateBy({it.lastName}) {it.age}
    println("map2 = $map2")

    val map3: Map<String, Person> =
        persons.associateBy { it.lastName }
    println("map3 = $map3")

    val map4 =
        persons.associateWith { it.age }
    println("map4 = $map4")

    //    joinToString - join elements of collection to a string
    val joined = persons.joinToString( "; ", transform = {it.firstName + " " + it.lastName})
    println("joined = ${joined}")
    val joinedTo = persons.joinTo(StringBuilder(), separator = "; ")
    println("joined by StringBuilder = ${joinedTo}")

    //    flatMap - maps each element to collection and then flattens then resulting collections
    val yearsLived =
        persons.flatMap { p -> 1 .. p.age }
    println("flatMap = ${yearsLived}")

    //    map without flatten
    val yearsLived2 =
        persons.map {p -> 1 .. p.age}
    println("flatten = ${yearsLived2}")

    //    flatten - flatten a collection of collections
    val yearsLived3 =
        persons.map {p -> 1 .. p.age} . flatten()
    println("flatten = ${yearsLived3}")

    // filtering ----------------------------------------------------------------------------------

    // filter, filterNot - filter elements fulfilling predicate
    val adults =
        persons.filterNot { it.age < 18 }
    println("adults = $adults")

    // filterIndexed - filter elements based on value and index
    val filteredIndex =
        persons.filterIndexed { i, p -> i % 2 == 1}
    println("filteredIndex = $filteredIndex")


    // Quantifiers --------------------------------------------------------------------------------

    // all - check if all fulfill predicate
    val allAdults =
        persons.all { it.age >= 18 }
    println("allAdults = $allAdults")

    // any - check if any element fulfills predicate
    val anyMinor =
        persons.any { it.age < 18 }
    println("anyMinor = $anyMinor")

    // none - check if none of the elements fulfills predicate
    val noneMinor =
        persons.none { it.age < 18 }
    println("noneMinor = $noneMinor")


    // Find an element ----------------------------------------------------------------------------

    // find, first, last - first, last element fulfilling criteria, throw exception if none found
    try {
        val anna = persons.first { it.firstName == "Anna" }
        println(anna)
    } catch (e: Exception) {
        println("Anna not found")
    }

    // find firstOrNull, lastOrNull	- first, last element fulfilling criteria, returns null if none found
    val lastSenior =
        persons.lastOrNull() { it.age >= 60 }
    println("lastSenior = ${lastSenior ?: "NN"}")

    // indexOfFirst, indexOfLast - position of first, last element fulfilling criteria, -1 if none found
    val lastSeniorIdx =
        persons.indexOfLast { it.age >= 60 }
    println("lastSeniorIdx = $lastSeniorIdx")


    // Sorting ------------------------------------------------------------------------------------

    // sortedBy, sortedByDescening - sort elements and returns sorted collection based on Comparator
    val sortedPersons =
        persons.sortedByDescending { it.age }
    println("sortedPersons = $sortedPersons")
    // compareBy - use compareBy for creating Comparator based on property

    // sort, sortDescening - sort elements in mutable collection based on Comparator
    val mPersons = persons.toMutableList()
    mPersons.sortWith(
        compareBy<Person> { it.age } . thenComparing { p -> p.lastName } . thenComparing { p -> p.firstName }
    )
    println("mutable sorted = $mPersons")


    // Reductions ---------------------------------------------------------------------------------

    // fold - computes a result from the elements starting with a initial value and an accumulator function
    val agesSum =
        persons.fold(0) {
                acc, p -> acc + p.age
        }
    println("agesSum = $agesSum")

    // reduce - computes a result from the elements starting with a first element and an accumulator function
    val agesSum2 = persons.map { it.age } .reduce { sum, a -> sum + a }
    println("agesSum2 = $agesSum2")

    val agesSum3 =  persons.map { it.age } .reduceOrNull() { sum, a -> sum + a }
    println("agesSum2 = ${agesSum2 ?: 0}")


    // Grouping -----------------------------------------------------------------------------------

    // groupBy	Group elements based on criteria
    val ageGroups =
        persons.groupBy ({ it.age }, { "${it.firstName} ${it.lastName}" })
    println("agesGroups = $ageGroups")

    // groupingBy - creates Grouping which allow further with processing elements in each group
    // eachCount	counts the elements in group
    val ageCounts =
        persons.groupingBy { it.age } .eachCount()
    println("agesCounts = $ageCounts")

    // fold and reduce - compute total string of lastnames
    val ageSum =
        persons.groupingBy { it.age } .fold (0) { sum, p -> sum + p.age }
    println("ageSums = $ageSum ")

    // aggregate - allows flexible processing of groups
    // (key: K, accumulator: R?, element: T, first: Boolean) -> R
    val ageGroupsNames =
        persons.groupingBy { it.age }
            .aggregate { age: Int, acc: String?, p: Person, first: Boolean -> if (first) "${p.firstName}" else "$acc - ${p.firstName}"}
    println("ageGroupsNames = $ageGroupsNames")

    // partition - partitions the elements based on predicate
    val (juniors, seniors) =
        persons.partition { it.age < 25 }
    println("juniors = $juniors, seniors = $seniors")

}