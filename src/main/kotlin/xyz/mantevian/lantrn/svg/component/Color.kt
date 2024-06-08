package xyz.mantevian.lantrn.svg.component

import xyz.mantevian.lantrn.svg.SvgNode
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.SvgUnit


interface Color

fun <T> T.color(
    fill: SvgPaint? = null,
    stroke: SvgPaint? = null,
    strokeWidth: SvgUnit? = null,
    fillOpacity: Double? = null,
    opacity: Double? = null
): T where T : SvgNode, T : Color {
    set("fill", fill)
    set("stroke", stroke)
    set("stroke-width", strokeWidth)
    set("fill-opacity", fillOpacity)
    set("opacity", opacity)
    return this
}
