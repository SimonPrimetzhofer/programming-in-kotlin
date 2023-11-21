sealed interface TaggedElement : Element {
    val closeTag
        get() = "</$tag>"
    val openTag
        get() = "<$tag>"

    val tag: String
}