package xyz.mantevian.lantrn.generator

import xyz.mantevian.lantrn.svg.*
import xyz.mantevian.lantrn.svg.component.color
import xyz.mantevian.lantrn.svg.component.gradient.addStop
import xyz.mantevian.lantrn.svg.component.gradient.center
import xyz.mantevian.lantrn.svg.component.gradient.focus
import xyz.mantevian.lantrn.svg.component.path
import xyz.mantevian.lantrn.svg.component.transform
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.percent
import xyz.mantevian.lantrn.svg.unit.px
import kotlin.math.PI
import kotlin.random.Random

class SimpleGridGenerator : SvgGenerator("simple_grid") {

    val n = 6
    val random = Random

    fun generate(): SVG {
        val w = 1.0 * width / n

        val svg = SVG(width, height)

        svg.addDefinition(RadialGradientNode().apply {
            id = "gradient1"
            focus(0.0.px, 0.0.px, 0.0.px)
            center(0.5.px, 0.5.px, 1.0.px)
            addStop(StopNode(offset = 0.0.percent, color = SvgPaint.RGB(255.0, 0.0, 0.0)))
            addStop(StopNode(offset = 100.0.percent, color = SvgPaint.RGB(0.0, 0.0, 255.0)))
        })

        val g = GroupNode()

        g.apply {

            addChild(
                PathNode()
                    .path(SvgPath().apply {
                        for (x in 0..<n) {
                            for (y in 0..<n) {

                                if (random.nextDouble() < 0.33) {
                                    move(x * w, y * w)
                                    lineRelative(w, w)
                                    lineRelative(-w, 0.0)
                                    lineRelative(0.0, -w)
                                }

                            }
                        }
                    })
                    .color(fill = SvgPaint.None, stroke = SvgPaint.Name("black"))
            )

            addChild(
                PathNode()
                    .path(SvgPath().apply {
                        for (x in 0..<n) {
                            for (y in 0..<n) {

                                if (random.nextDouble() < 0.33) {
                                    move(x * w, y * w)
                                    lineRelative(w, 0.0)
                                    lineRelative(0.0, -w)
                                    lineRelative(-w, 0.0)
                                    lineRelative(0.0, w)
                                }

                            }
                        }
                    })
                    .color(fill = SvgPaint.Name("black"), stroke = SvgPaint.None, fillOpacity = 0.2)
            )

            addChild(
                PathNode()
                    .path(SvgPath().apply {
                        for (x in 0..<n) {
                            for (y in 0..<n) {

                                if (random.nextDouble() < 0.33) {
                                    move(x * w, y * w)
                                    circleRelative(w, 0.0, -PI * 0.5)
                                    lineRelative(-w, 0.0)
                                    lineRelative(0.0, w)
                                }

                            }
                        }
                    })
                    .color(fill = SvgPaint.FromId("gradient1"))
            )
        }

        svg.root.addChild(g)
        return svg
    }

}