package xyz.mantevian.lantrn

import java.net.URL

object ResourceUtil {
	fun getResource(path: String): URL? {
		return this.javaClass.getResource(path)
	}

	fun getTextFile(path: String): String? {
		return this.javaClass.getResourceAsStream(path)?.bufferedReader()?.readLines()?.joinToString("")
	}
}