package xyz.mantevian.lantrn.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
	install(StatusPages) {
		status(HttpStatusCode.NotFound) { call, code ->
			call.respondText(text = code.toString())
		}
	}

	routing {
		staticResources("/static", "static") {
			cacheControl { url ->
				when {
					url.file.endsWith(".ttf") -> listOf(CacheControl.MaxAge(60 * 60 * 24 * 7))
					url.file.endsWith(".svg") -> listOf(CacheControl.MaxAge(60 * 60 * 24))
					url.file.endsWith(".css") -> listOf(CacheControl.MaxAge(60 * 60 * 24))
					else -> emptyList()
				}
			}
		}

		get("robots.txt") {
			call.respond("User-agent: GPTBot\nDisallow: /")
		}
	}
}
