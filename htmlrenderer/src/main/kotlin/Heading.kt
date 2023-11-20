data class Heading(override val text: String, val level: Int = 1) : TaggedTextElement {
    override val tag
        get() = "h${level}>"

    init {
        if (level !in 1..6) {
            throw Error("level $level not in range 1 to 6!")
        }
    }
}
