data class ListItem(override val elements: List<Element>) : ContainerElement {
    override val tag: String
        get() = "li"

    constructor(vararg elements: Element) : this (elements.asList())
}
