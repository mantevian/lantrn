package xyz.mantevian.lantrn

import io.ktor.server.application.*
import io.ktor.server.plugins.cachingheaders.*
import io.ktor.server.plugins.compression.*
import xyz.mantevian.lantrn.localization.Localization
import xyz.mantevian.lantrn.plugins.configureRouting
import xyz.mantevian.lantrn.plugins.configureSerialization
import xyz.mantevian.lantrn.plugins.configureTemplating

fun main(args: Array<String>) {
	io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
	install(CachingHeaders)
	install(Compression)

	configureSerialization()
	configureTemplating()
	configureRouting()

	Localization.reload()
}
