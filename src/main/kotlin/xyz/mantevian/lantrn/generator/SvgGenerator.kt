package xyz.mantevian.lantrn.generator

import xyz.mantevian.lantrn.svg.SVG

abstract class SvgGenerator(val name: String) {
	val width: Int = 800
	val height: Int = 800

	val svg = SVG(width, height)
}