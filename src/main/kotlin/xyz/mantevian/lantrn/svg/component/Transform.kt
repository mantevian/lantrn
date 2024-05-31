package xyz.mantevian.lantrn.svg.component

import xyz.mantevian.lantrn.svg.SvgTransform
import xyz.mantevian.lantrn.svg.SvgNode

interface Transform

fun <T> T.transform(
    transform: SvgTransform
): T where T : SvgNode, T : Transform {
    set("transform", transform)
    return this
}
