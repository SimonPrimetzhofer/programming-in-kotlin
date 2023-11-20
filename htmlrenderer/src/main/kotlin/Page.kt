data class Page(val title: String, val elements: Array<Element>) {
    constructor(title: String, vararg elements: Element)
        : this(title, elements.asList().toTypedArray())
}
