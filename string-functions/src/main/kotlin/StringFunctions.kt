fun main() {
    // #1 dropLastWhile
    // the function drops all character starting from the back of the original string
    // which fulfill the predicate of being equal to #
    // thus it prints "kotlin is cool"
    println(
        "kotlin is cool##################".dropLastWhile { it == '#' }
    )

    // #2 elementAtOrElse
    // the function does returns the character at the supplied index or,
    // if the supplied index is out of bounds, returns a character that
    // is determined by the supplied defaultValue lambda
    // in this case, i just return the corresponding char of the supplied index value
    println(
        "wos isn".elementAtOrElse(42) {
            it.toChar()
        }
    )

    // #3 filter
    // the function creates a string containing only those characters
    // of the original string that fulfill the supplied predicate
    // in this case i remove all "odd" characters according to their numeric ascii value
    println(
        "abcdefghijklmnopqrstuvwxyz".filter { Character.getNumericValue(it) % 2 == 0 }
    )

    // #4 first
    // the function returns the first character of the given string
    // ':' in this case
    println(
        ":(){ :|:& };:".first()
    )

    // #5 map
    // the function maps each character to the value of the supplied transform function
    // and returns a list with those entries
    // in this case i duplicate every character and join the new values to a string
    // where each entry is separated by a whitespace
    println(
        "69420noice".map { "$it$it" }.joinToString (" "){ it }
    )

    // #6 none
    // the function return true if the given string is empty
    // false in this case
    println(
        "zeichenstrom -> tokenstrom".none()
    )

    // #7 onEachIndexed
    // the function performs the given action for each character and returns
    // the original string itself afterwards
    // in this case i print each character with its index in teh string
    "joe mama".onEachIndexed { index, c -> println("$c at index $index") }

    // #8 padEnd
    // the function fills a new string with spaces in the beginning until
    // the strin reaches the supplied length (40 in this case)
    println(
        "need some space".padStart(40)
    )

    // #9 partition
    // the function creates a pair of strings
    // first pair element contains characters, for which the predicate is true
    // second pair element contains characters, for which the predicate is false
    println(
        "zeichenstrom > tokenstrom".partition { it == '>' }
    )

    // #10 random
    // the function returns a random character from the given string
    println(
        "is it really random?".random()
    )
}