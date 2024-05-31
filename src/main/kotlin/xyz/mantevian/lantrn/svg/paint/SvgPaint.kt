package xyz.mantevian.lantrn.svg.paint

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

    data class RGB(val r: Double, val g: Double, val b: Double) : SvgPaint {
        override fun toString(): String {
            return "rgb($r,$g,$b)"
        }
    }

    data class FromId(val id: String) : SvgPaint {
        override fun toString(): String {
            return "url(#$id)"
        }
    }
}