data class Div(override val elements: List<Element>) : ContainerElement {
    constructor(vararg items: Element)
            : this (items.asList())

    override val tag: String
        get() = "div"
}
