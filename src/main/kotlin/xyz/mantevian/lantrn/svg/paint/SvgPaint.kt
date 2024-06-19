package xyz.mantevian.lantrn.svg.paint

import xyz.mantevian.lantrn.svg.color.Color

sealed interface SvgPaint {
	data object None : SvgPaint {
		override fun toString(): String {
			return "none"
		}
	}

	data class Name(val value: String) : SvgPaint {
		override fun toString(): String {
			return value
		}
	}

	data class FromColor(val color: Color) : SvgPaint {
		override fun toString(): String {
			return color.toString()
		}
	}

	data class FromId(val id: String) : SvgPaint {
		override fun toString(): String {
			return "url(#$id)"
		}
	}
}