package xyz.mantevian.lantrn.svg.unit

interface SvgUnit

class PxUnit(private val value: Double) : SvgUnit {
    override fun toString(): String {
        return "$value"
    }
}

class PercentUnit(private val value: Double) : SvgUnit {
    override fun toString(): String {
        return "${value}%"
    }
}

val Double.px: PxUnit
    get() = PxUnit(this)

val Double.percent: PercentUnit
    get() = PercentUnit(this)