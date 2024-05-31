package xyz.mantevian.lantrn.svg.component.gradient

import xyz.mantevian.lantrn.svg.StopNode
import xyz.mantevian.lantrn.svg.SvgNode
import xyz.mantevian.lantrn.svg.unit.SvgUnit

interface LinearGradient

fun <T> T.addStop(stopNode: StopNode): T where T : SvgNode, T : LinearGradient {
    addChild(stopNode)
    return this
}

fun <T> T.start(x: SvgUnit, y: SvgUnit): T where T : SvgNode, T : LinearGradient {
    set("x1", x)
    set("y1", y)
    return this
}

fun <T> T.end(x: SvgUnit, y: SvgUnit): T where T : SvgNode, T : LinearGradient {
    set("x2", x)
    set("y2", y)
    return this
}

fun <T> T.spread(type: GradientSpread): T where T : SvgNode, T : LinearGradient {
    set("spreadMethod", type.value)
    return this
}