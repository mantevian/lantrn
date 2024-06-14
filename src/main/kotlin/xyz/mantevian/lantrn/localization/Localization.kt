package xyz.mantevian.lantrn.localization

import kotlinx.serialization.json.Json
import xyz.mantevian.lantrn.ResourceUtil

object Localization {
	private val languages = listOf("en_us")
	private val locales: MutableMap<String, Locale> = mutableMapOf()

	fun reload() {
		for (l in languages) {
			locales[l] = Locale(Json.decodeFromString(ResourceUtil.readFile("/static/localization/${l}.json") ?: ""))
		}
	}

	fun localize(localeName: String, key: String): String = locales[localeName]?.stringMap?.get(key) ?: key
}

data class Locale(val stringMap: Map<String, String>)