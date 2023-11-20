data class Div(override val elements: List<Element>, val ordered: Boolean) : ContainerElement {

    constructor(ordered: Boolean, vararg items: ListItem)
            : this (items.asList(), ordered)

    override val tag: String
        get() = "div"
}
