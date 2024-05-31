package xyz.mantevian.lantrn

import io.ktor.server.application.*
import xyz.mantevian.lantrn.plugins.*
import io.ktor.server.plugins.cachingheaders.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(CachingHeaders)

    configureSerialization()
    configureTemplating()
    configureRouting()
}
