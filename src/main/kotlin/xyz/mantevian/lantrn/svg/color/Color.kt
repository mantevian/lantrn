package xyz.mantevian.lantrn.svg.color

import xyz.mantevian.lantrn.svg.unit.SvgUnit
import xyz.mantevian.lantrn.svg.unit.percent
import kotlin.random.Random

sealed interface Color {

	data class RGB(var r: Double, var g: Double, var b: Double) : Color {
		override fun toString(): String {
			return "rgb($r,$g,$b)"
		}

		companion object {
			fun getRandomColor(random: Random): RGB {
				return RGB(
					random.nextDouble(0.0, 255.0),
					random.nextDouble(0.0, 255.0),
					random.nextDouble(0.0, 255.0)
				)
			}
		}
	}

	data class HSL(var h: Double, var s: SvgUnit.Percent, var l: SvgUnit.Percent) : Color {
		override fun toString(): String {
			return "hsl($h,$s,$l)"
		}

		companion object {
			fun getRandomColor(random: Random): HSL {
				return HSL(
					random.nextDouble(0.0, 255.0),
					random.nextDouble(0.0, 100.0).percent,
					random.nextDouble(0.0, 100.0).percent
				)
			}
		}
	}

	data class OkLCH(var l: SvgUnit.Percent, var c: Double, var h: Double) : Color {
		override fun toString(): String {
			return "oklch($l,$c,$h)"
		}

		companion object {
			fun getRandomColor(random: Random): OkLCH {
				return OkLCH(
					random.nextDouble(0.0, 100.0).percent,
					random.nextDouble(0.0, 0.34),
					random.nextDouble(0.0, 360.0)
				)
			}
		}
	}

}