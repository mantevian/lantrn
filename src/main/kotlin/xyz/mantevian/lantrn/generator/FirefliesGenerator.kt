package xyz.mantevian.lantrn.generator

import xyz.mantevian.lantrn.svg.*
import xyz.mantevian.lantrn.svg.component.*
import xyz.mantevian.lantrn.svg.component.gradient.addStop
import xyz.mantevian.lantrn.svg.component.gradient.center
import xyz.mantevian.lantrn.svg.component.gradient.focus
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.percent
import xyz.mantevian.lantrn.svg.unit.px

class FirefliesGenerator : SvgGenerator("fireflies") {
	fun generate(n: Int): SVG {
		val w = 800.0 / n

		val svg = SVG(width, height)

		svg.addDefinition(RadialGradientNode().apply {
			id = "gradient1"
			focus(centerX = 0.0.px, centerY = 0.0.px, radius = 0.0.px)
			center(centerX = 0.5.px, centerY = 0.5.px, radius = 1.0.px)
			addStop(StopNode(offset = 0.0.percent, color = SvgPaint.RGB(random.nextDouble(0.0, 255.0), random.nextDouble(0.0, 255.0), random.nextDouble(0.0, 255.0))))
			addStop(StopNode(offset = 100.0.percent, color = SvgPaint.RGB(random.nextDouble(0.0, 255.0), random.nextDouble(0.0, 255.0), random.nextDouble(0.0, 255.0))))
		})

		svg.root.addChild(RectNode().apply {
			position(0.0.px, 0.0.px)
			size(800.0.px, 800.0.px)
			color(fill = SvgPaint.FromId("gradient1"))
		})

		val firefly = SymbolNode().apply {
			addChild(PathNode().apply {
				id = "firefly"
				set(
					"d",
					"M 6.5613566,1.2820923 C 5.8076277,1.2760223 5.520561,1.7738464 5.3433431,2.7367839 5.2161564,2.5886651 5.0512428,2.5286026 4.8410482,2.5869222 3.514798,2.954897 0.18265107,10.228496 1.0883057,11.666988 1.9939603,13.105481 4.6190472,9.8606109 5.1624756,8.2020833 5.2144266,8.0435302 5.2659626,7.860333 5.3149211,7.6594808 5.9227168,8.4262896 7.0705105,8.4126949 7.6615479,7.5783488 7.7162339,7.8107431 7.7738315,8.0227333 7.8325968,8.2020833 8.3760251,9.860611 11.001628,13.105481 11.907284,11.666988 12.812939,10.228496 9.4807914,2.954897 8.154541,2.5869222 7.9799399,2.5384782 7.8364744,2.5721827 7.7199422,2.6696045 7.5796585,1.7290305 7.3193009,1.2881962 6.5613566,1.2820923 Z"
				)
			})
		}

		svg.root.addChild(firefly)


		for (i in 0..5) {
			svg.root.addChild(PathNode().apply {
				color(fill = SvgPaint.None, stroke = SvgPaint.RGB(random.nextDouble(255.0), random.nextDouble(255.0), random.nextDouble(255.0)), strokeWidth = random.nextDouble(1.0, 5.0).px, opacity = random.nextDouble(0.05, 0.2))
				path(SvgPath().apply {
					move(0.0, 0.0)
					cubic(random.nextDouble(-200.0, 1000.0), 400.0, random.nextDouble(-200.0, 1000.0), 400.0, 800.0, 800.0)
				})
			})
		}

		var x = 0.0
		var y = 200.0
		var r = 135.0
		val color = SvgPaint.RGB(random.nextDouble(255.0), random.nextDouble(255.0), random.nextDouble(255.0))

		for (i in 0..n) {

			x += w
			y += (800.0 - y) * random.nextDouble(-0.1, 0.2)
			r += random.nextDouble(-25.0, 25.0)

			svg.root.addChild(UseNode("#firefly").apply {
				color(fill = color, fillOpacity = random.nextDouble(0.7, 1.0))

				transform(SvgTransform().apply {
					translate(x, y + random.nextDouble(-100.0, 100.0))
					scale(random.nextDouble(1.0, 10.0))
					rotate(r)
				})
			})

		}


		return svg
	}
}