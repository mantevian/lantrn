package xyz.mantevian.lantrn.generator

import kotlin.random.Random

abstract class SvgGenerator(val name: String) {
    protected val random = Random

    val width: Int = 800
    val height: Int = 800
}