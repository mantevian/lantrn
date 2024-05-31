package xyz.mantevian.lantrn.svg

object Encoder {

    fun encode(svg: SVG): String {
        val builder = StringBuilder()

        return builder.apply {
            //append("""<?xml version="1.0" encoding="UTF-8"?>""")
            append(encode(svg.root))
        }.toString()
    }

    private fun encode(node: SvgNode): String {
        val builder = StringBuilder()

        return builder.apply {
            append("<${node.name} ")
            append(node.attributes.map { "${it.key}=\"${it.value}\"" }.joinToString(separator = " "))
            append(">")
            append(node.children.joinToString(separator = "") { encode(it) })
            append("</${node.name}>")
        }.toString()
    }

}