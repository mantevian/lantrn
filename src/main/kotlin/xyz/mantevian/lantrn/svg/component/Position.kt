package xyz.mantevian.lantrn.svg.component

import xyz.mantevian.lantrn.svg.SvgNode
import xyz.mantevian.lantrn.svg.unit.SvgUnit


interface Position

fun <T> T.position(
    x: SvgUnit? = null,
    y: SvgUnit? = null
): T where T : SvgNode, T : Position {
    set("x", x)
    set("y", y)
    return this
}