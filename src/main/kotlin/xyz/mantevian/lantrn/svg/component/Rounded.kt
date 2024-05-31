package xyz.mantevian.lantrn.svg.component

import xyz.mantevian.lantrn.svg.SvgNode
import xyz.mantevian.lantrn.svg.unit.SvgUnit


interface Rounded

fun <T> T.round(
    rx: SvgUnit? = null,
    ry: SvgUnit? = null
): T where T : SvgNode, T : Rounded {
    set("rx", rx)
    set("ry", ry)
    return this
}