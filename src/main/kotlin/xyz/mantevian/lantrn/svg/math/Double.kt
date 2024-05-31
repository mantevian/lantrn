package xyz.mantevian.lantrn.svg.math

import kotlin.math.abs

infix fun Double.eq(other: Double): Boolean {
    return abs(other - this) < 0.0001
}