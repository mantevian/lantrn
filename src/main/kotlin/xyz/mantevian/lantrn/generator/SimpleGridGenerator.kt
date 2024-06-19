package xyz.mantevian.lantrn.generator

import xyz.mantevian.lantrn.svg.*
import xyz.mantevian.lantrn.svg.color.Color
import xyz.mantevian.lantrn.svg.component.color
import xyz.mantevian.lantrn.svg.component.gradient.addStop
import xyz.mantevian.lantrn.svg.component.gradient.center
import xyz.mantevian.lantrn.svg.component.gradient.focus
import xyz.mantevian.lantrn.svg.component.path
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.percent
import xyz.mantevian.lantrn.svg.unit.px
import kotlin.random.Random
import kotlin.random.nextInt

class SimpleGridGenerator(
	seed: Int = 0
) : SvgGenerator("simple_grid") {

	private val random = Random(seed)

	fun generate(n: Int): SVG {
		val w = 1.0 * width / n

		svg.addDefinition(RadialGradientNode("gr").apply {
			focus(centerX = 0.0.px, centerY = 0.0.px, radius = 0.0.px)
			center(centerX = 0.5.px, centerY = 0.5.px, radius = 1.0.px)
			addStop(
				StopNode(
					offset = 0.0.percent,
					color = SvgPaint.FromColor(Color.RGB.getRandomColor(random))
				)
			)
			addStop(
				StopNode(
					offset = 100.0.percent,
					color = SvgPaint.FromColor(Color.RGB.getRandomColor(random))
				)
			)
		})

		val g = GroupNode()

		g.apply {

			addChild(
				PathNode()
					.path(SvgPath().apply {
						for (x in 0..<n) {
							for (y in 0..<n) {
								when (random.nextInt(1..15)) {
									1 -> {
										move(x * w, y * w)
										lineRelative(w, w)
										lineRelative(-w, 0.0)
										lineRelative(0.0, -w)
									}

									2 -> {
										move(x * w, y * w)
										lineRelative(w, 0.0)
										lineRelative(-w, w)
										lineRelative(0.0, -w)
									}

									3 -> {
										move(x * w, y * w)
										lineRelative(w, 0.0)
										lineRelative(0.0, w)
										lineRelative(-w, -w)
									}

									4 -> {
										move(x * w, y * w)
										moveRelative(w, 0.0)
										lineRelative(0.0, w)
										lineRelative(-w, 0.0)
										lineRelative(w, -w)
									}

									in 5..8 -> {
										move(x * w, y * w)
										lineRelative(w, 0.0)
										lineRelative(0.0, w)
										lineRelative(-w, 0.0)
										lineRelative(0.0, -w)
									}
								}
							}
						}
					})
					.color(fill = SvgPaint.FromId("gr"), stroke = SvgPaint.Name("black"))
			)
		}

		svg.root.addChild(g)
		return svg
	}

}