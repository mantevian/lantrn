package xyz.mantevian.lantrn.svg

import xyz.mantevian.lantrn.svg.component.*
import xyz.mantevian.lantrn.svg.component.gradient.LinearGradient
import xyz.mantevian.lantrn.svg.component.gradient.RadialGradient
import xyz.mantevian.lantrn.svg.paint.SvgPaint
import xyz.mantevian.lantrn.svg.unit.SvgUnit

class RootNode(viewBox: String, width: SvgUnit?, height: SvgUnit?) : SvgNode("svg") {
	init {
		set("version", "1.1")
		set("xmlns", "http://www.w3.org/2000/svg")
		set("viewBox", viewBox)
		set("width", width)
		set("height", height)
	}
}

class DefsNode : SvgNode("defs")

class RectNode : SvgNode("rect"), Position, Size, Rounded, Color, Transform

class CircleNode(cx: SvgUnit, cy: SvgUnit, r: SvgUnit) : SvgNode("circle"), Color, Transform {
	init {
		set("cx", cx)
		set("cy", cy)
		set("r", r)
	}
}

class GroupNode : SvgNode("g"), Color, Transform

class PathNode : SvgNode("path"), Path, Color, Transform

class LinearGradientNode(id: String) : SvgNode("linearGradient"), LinearGradient {
	init {
		this.id = id
	}
}

class RadialGradientNode(id: String) : SvgNode("radialGradient"), RadialGradient {
	init {
		this.id = id
	}
}

class StopNode(offset: SvgUnit? = null, color: SvgPaint.FromColor? = null, opacity: Double? = null) : SvgNode("stop") {
	init {
		set("offset", offset)
		set("stop-color", color)
		set("stop-opacity", opacity)
	}
}

class UseNode(href: String) : SvgNode("use"), Color, Transform, Position, Size {
	init {
		set("href", href)
	}
}

class SymbolNode : SvgNode("symbol"), Position, Size