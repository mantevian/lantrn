package xyz.mantevian.lantrn.svg.component

import xyz.mantevian.lantrn.svg.SvgPath
import xyz.mantevian.lantrn.svg.SvgNode


interface Path

fun <T> T.path(
    d: SvgPath
): T where T : SvgNode, T : Path {
    set("d", d)
    return this
}
