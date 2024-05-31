package xyz.mantevian.lantrn.markdown

import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser

object MarkdownParser {
    private val flavour = CommonMarkFlavourDescriptor()

    fun parse(text: String): String {
        val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(text)
        return HtmlGenerator(text, parsedTree, flavour).generateHtml()
    }
}

