package xyz.mantevian.lantrn.svg.component

import xyz.mantevian.lantrn.svg.SvgNode
import xyz.mantevian.lantrn.svg.unit.SvgUnit


interface Size

fun <T> T.size(
    width: SvgUnit? = null,
    height: SvgUnit? = null
): T where T : SvgNode, T : Size {
    set("width", width)
    set("height", height)
    return this
}
