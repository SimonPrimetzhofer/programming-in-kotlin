object HTMLRenderer {
    private fun render(element: Element): String = when (element) {
        is Text -> "\n${element.text}".indentEachLine()
        is ContainerElement -> element.elements.joinToString(
            "",
            "\n${element.openTag}",
            "\n${element.closeTag}"
        ) { render(it) }.indentEachLine()

        is TaggedTextElement -> "\n${element.openTag}${element.text}${element.closeTag}".indentEachLine()
    }

    fun render(page: Page): String {
        return "<html>" +
                ("\n<head>" +
                        "\n<title>${page.title}</title>".indentEachLine() +
                        "\n</head>" +
                        "\n<body>" +
                        page.elements.joinToString("") { render(it) } +
                        "\n</body>").indentEachLine() +
                "\n</html>"
    }
}