package xyz.mantevian.lantrn.svg

import xyz.mantevian.lantrn.svg.math.eq
import kotlin.math.*

class SvgPath {

    private val builder: StringBuilder = StringBuilder()

    private var cursorX: Double = 0.0
    private var cursorY: Double = 0.0

    override fun toString(): String {
        return builder.toString()
    }

    fun move(x: Double, y: Double): SvgPath {
        builder.append(" M $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun moveRelative(dx: Double, dy: Double): SvgPath {
        builder.append(" m $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun line(x: Double, y: Double): SvgPath {
        builder.append(" L $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun lineRelative(dx: Double, dy: Double): SvgPath {
        builder.append(" l $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun cubic(x1: Double, y1: Double, x2: Double, y2: Double, x: Double, y: Double): SvgPath {
        builder.append(" C $x1 $y1, $x2 $y2, $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun cubicRelative(dx1: Double, dy1: Double, dx2: Double, dy2: Double, dx: Double, dy: Double): SvgPath {
        builder.append(" c $dx1 $dy1, $dx2 $dy2, $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun cubic(x2: Double, y2: Double, x: Double, y: Double): SvgPath {
        builder.append(" S $x2 $y2, $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun cubicRelative(dx2: Double, dy2: Double, dx: Double, dy: Double): SvgPath {
        builder.append(" s $dx2 $dy2, $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun quad(x1: Double, y1: Double, x: Double, y: Double): SvgPath {
        builder.append(" Q $x1 $y1, $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun quadRelative(dx1: Double, dy1: Double, dx: Double, dy: Double): SvgPath {
        builder.append(" q $dx1 $dy1, $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun quad(x: Double, y: Double): SvgPath {
        builder.append(" T $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun quadRelative(dx: Double, dy: Double): SvgPath {
        builder.append(" t $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun arc(radiusX: Double, radiusY: Double, x: Double, y: Double, rotation: Double, largeArc: Boolean, sweep: Boolean): SvgPath {
        builder.append(" A $radiusX $radiusY $rotation ${if (largeArc) 1 else 0} ${if (sweep) 1 else 0} $x $y")
        cursorX = x
        cursorY = y
        return this
    }

    fun arcRelative(radiusX: Double, radiusY: Double, dx: Double, dy: Double, rotation: Double, largeArc: Boolean, sweep: Boolean): SvgPath {
        builder.append(" A $radiusX $radiusY $rotation ${if (largeArc) 1 else 0} ${if (sweep) 1 else 0} $dx $dy")
        cursorX += dx
        cursorY += dy
        return this
    }

    fun circle(centerX: Double, centerY: Double, angle: Double): SvgPath {
        var actualAngle = min(abs(angle), 2 * PI - 0.0001) * sign(angle)
        if (abs(actualAngle) eq (2 * PI)) {
            actualAngle = 2 * PI - 0.0001
        }

        val dx = cursorX - centerX
        val dy = cursorY - centerY

        val radius = sqrt(dx * dx + dy * dy)

        val s = sin(actualAngle)
        val c = cos(actualAngle)

        val endX = dx * c - dy * s + centerX
        val endY = dx * s - dy * c + centerY

        arc(radius, radius, endX, endY, 0.0, largeArc = abs(actualAngle) > PI, sweep = actualAngle > 0)

        return this
    }

    fun circleRelative(centerDX: Double, centerDY: Double, angle: Double): SvgPath {
        circle(cursorX + centerDX, cursorY + centerDY, angle)

        return this
    }

}