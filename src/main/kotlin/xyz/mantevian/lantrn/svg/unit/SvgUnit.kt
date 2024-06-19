package xyz.mantevian.lantrn.svg.unit

import kotlin.math.floor

sealed interface SvgUnit {
	class Px(private val value: Double) : SvgUnit {
		override fun toString(): String {
			return "${floor(value * 100) * 0.01}"
		}
	}

	class Percent(private val value: Double) : SvgUnit {
		override fun toString(): String {
			return "${floor(value * 100) * 0.01}%"
		}
	}
}


val Double.px: SvgUnit.Px
	get() = SvgUnit.Px(this)

val Double.percent: SvgUnit.Percent
	get() = SvgUnit.Percent(this)