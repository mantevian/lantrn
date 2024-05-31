package xyz.mantevian.lantrn.localization

import kotlinx.serialization.json.Json
import java.io.File

object Localization {

    private val locales: MutableMap<String, Locale> = mutableMapOf()

    init {
        reload()
    }

    fun reload() {
        val resource = this.javaClass.classLoader.getResource("static/localization")
        resource?.let {
            locales.clear()

            val dir = File(resource.file)
            val localeFiles = dir.walk().filter { it.isFile }

            localeFiles.forEach {
                val fileText = it.readText()
                locales[it.nameWithoutExtension] = Locale(Json.decodeFromString(fileText))
            }
        }
    }

    fun localize(localeName: String, key: String): String = locales[localeName]?.stringMap?.get(key) ?: key
}

data class Locale(val stringMap: Map<String, String>)