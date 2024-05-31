package xyz.mantevian.lantrn.svg.component.gradient

import xyz.mantevian.lantrn.svg.StopNode
import xyz.mantevian.lantrn.svg.SvgNode
import xyz.mantevian.lantrn.svg.unit.SvgUnit

interface RadialGradient

fun <T> T.addStop(stopNode: StopNode): T where T : SvgNode, T : RadialGradient {
    addChild(stopNode)
    return this
}

fun <T> T.focus(centerX: SvgUnit, centerY: SvgUnit, radius: SvgUnit? = null): T where T : SvgNode, T : RadialGradient {
    set("fx", centerX)
    set("fy", centerY)
    set("fr", radius)
    return this
}

fun <T> T.center(centerX: SvgUnit, centerY: SvgUnit, radius: SvgUnit? = null): T where T : SvgNode, T : RadialGradient {
    set("cx", centerX)
    set("cy", centerY)
    set("r", radius)
    return this
}

fun <T> T.spread(type: GradientSpread): T where T : SvgNode, T : RadialGradient {
    set("spreadMethod", type.value)
    return this
}