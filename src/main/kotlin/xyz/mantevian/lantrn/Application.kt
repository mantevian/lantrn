package xyz.mantevian.lantrn

import io.ktor.server.application.*
import xyz.mantevian.lantrn.plugins.*
import io.ktor.server.plugins.cachingheaders.*
import xyz.mantevian.lantrn.localization.Localization

fun main(args: Array<String>) {
	io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
	install(CachingHeaders)

	configureSerialization()
	configureTemplating()
	configureRouting()

	Localization.reload()
}
