package xyz.mantevian.lantrn.generator

import xyz.mantevian.lantrn.svg.*
import xyz.mantevian.lantrn.svg.component.*
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.px
import kotlin.random.Random

class WavesGenerator(
	seed: Int = 0
) : SvgGenerator("fireflies") {

	private val random = Random(seed)

	fun generate(n: Int): SVG {
		val w = 800.0 / n

		val svg = SVG(width, height)

		svg.root.addChild(RectNode().apply {
			position(0.0.px, 0.0.px)
			size(800.0.px, 800.0.px)
			color(fill = SvgPaint.RGB(random.nextDouble(50.0), random.nextDouble(50.0), random.nextDouble(50.0)))
		})

		for (i in 0..n) {
			svg.root.addChild(PathNode().apply {
				color(fill = SvgPaint.RGB(random.nextDouble(255.0), random.nextDouble(255.0), random.nextDouble(255.0)), opacity = random.nextDouble(0.05, 0.5))
				path(SvgPath().apply {
					move(0.0, 0.0)
					cubic(random.nextDouble(-200.0, 1000.0), 400.0, random.nextDouble(-200.0, 1000.0), 400.0, 800.0, 800.0)
				})
			})
		}

		return svg
	}
}