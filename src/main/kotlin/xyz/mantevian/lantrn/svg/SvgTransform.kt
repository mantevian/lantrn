package xyz.mantevian.lantrn.svg

class SvgTransform {

    private val builder: StringBuilder = StringBuilder()

    override fun toString(): String {
        return builder.toString()
    }

    fun translate(x: Double): SvgTransform {
        builder.append(" translate($x)")
        return this
    }

    fun translate(x: Double, y: Double): SvgTransform {
        builder.append(" translate($x, $y)")
        return this
    }

    fun rotate(a: Double): SvgTransform {
        builder.append(" rotate($a)")
        return this
    }

    fun rotate(a: Double, x: Double, y: Double): SvgTransform {
        builder.append(" rotate($a, $x, $y)")
        return this
    }

    fun scale(x: Double): SvgTransform {
        builder.append(" scale($x)")
        return this
    }


    fun scale(x: Double, y: Double): SvgTransform {
        builder.append(" scale($x, $y)")
        return this
    }

    fun skewX(a: Double): SvgTransform {
        builder.append(" skewX($a)")
        return this
    }

    fun skewY(a: Double): SvgTransform {
        builder.append(" skewY($a)")
        return this
    }

    fun matrix(a: Double, b: Double, c: Double, d: Double, e: Double, f: Double): SvgTransform {
        builder.append(" matrix($a, $b, $c, $d, $e, $f)")
        return this
    }
}