package xyz.mantevian.lantrn.generator

import xyz.mantevian.lantrn.svg.CircleNode
import xyz.mantevian.lantrn.svg.RectNode
import xyz.mantevian.lantrn.svg.SVG
import xyz.mantevian.lantrn.svg.color.Color
import xyz.mantevian.lantrn.svg.component.color
import xyz.mantevian.lantrn.svg.component.position
import xyz.mantevian.lantrn.svg.component.size
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.percent
import xyz.mantevian.lantrn.svg.unit.px
import kotlin.random.Random

class SnakeGenerator(
	seed: Int = 0
) : SvgGenerator("snake") {

	private val random = Random(seed)

	fun generate(n: Int): SVG {

		var currX = random.nextDouble(width * 0.4, width * 0.6)
		var currY = random.nextDouble(height * 0.4, height * 0.6)

		val dist = 2000.0 / n

		var targetX = random.nextDouble(width * 0.1, width * 0.9)
		var targetY = random.nextDouble(height * 0.1, height * 0.9)

		val hStart = random.nextDouble(360.0)
		val sStart = random.nextDouble(70.0, 100.0)
		val lStart = random.nextDouble(40.0, 60.0)
		var hOffset = 0.0
		var lOffset = 0.0
		val hStep = random.nextDouble(360.0) / n
		val lStep = 30.0 / n

		svg.root.addChild(RectNode().apply {
			position(0.0.px, 0.0.px)
			size(width.toDouble().px, height.toDouble().px)
			color(stroke = null, fill = SvgPaint.FromColor(Color.HSL(hStart, sStart.percent, 20.0.percent)))
		})

		for (i in 1..n) {
			val circle = CircleNode(currX.px, currY.px, 50.0.px).apply {
				color(
					fill = SvgPaint.FromColor(Color.HSL(hStart + hOffset, sStart.percent, (lStart + lOffset).percent)),
					stroke = null
				)
			}

			svg.root.addChild(circle)

			if (random.nextDouble() < 0.01) {
				targetX = random.nextDouble(width * 0.1, width * 0.9)
				//targetSpeedX = targetSpeedX * 0.99 + (width * 0.5 - currX) * 0.01
			}

			if (random.nextDouble() < 0.01) {
				targetY = random.nextDouble(height * 0.1, height * 0.9)
				//targetSpeedY = targetSpeedY * 0.99 + (height * 0.5 - currY) * 0.01
			}

			currX = currX * 0.95 + targetX * 0.05
			currY = currY * 0.95 + targetY * 0.05

			hOffset += hStep
			lOffset += lStep
		}

		return svg
	}
}