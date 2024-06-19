package xyz.mantevian.lantrn.svg

import xyz.mantevian.lantrn.svg.unit.px


class SVG(width: Int, height: Int) {

	val root = RootNode("0 0 $width $height", (width.toDouble()).px, (height.toDouble()).px)
	private val defs = DefsNode()

	init {
		root.addChild(defs)
	}

	fun addDefinition(node: SvgNode) {
		defs.addChild(node)
	}

}