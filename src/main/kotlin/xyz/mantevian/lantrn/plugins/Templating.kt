package xyz.mantevian.lantrn.plugins

import gg.jte.TemplateEngine
import gg.jte.resolve.DirectoryCodeResolver
import gg.jte.resolve.ResourceCodeResolver
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.jte.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xyz.mantevian.lantrn.article.Article
import xyz.mantevian.lantrn.generator.SimpleGridGenerator
import xyz.mantevian.lantrn.item.Item
import xyz.mantevian.lantrn.svg.Encoder
import xyz.mantevian.lantrn.svg.SVG
import java.nio.file.Path
import java.util.*

fun Application.configureTemplating() {
    install(Jte) {
        val resolver = ResourceCodeResolver("templates")
        templateEngine = TemplateEngine.create(resolver, gg.jte.ContentType.Html)
    }

    val articles = mutableListOf<Article>()
    val items = mutableListOf<Item>()

    for (i in 0..4) {
        articles.add(Article(i, "hello world", Date(), "this is a description",
            "## Heading2 \n\n hello world \n\n Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod  tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim  veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea  commodo consequat. Duis aute irure dolor in reprehenderit in voluptate  velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint  occaecat cupidatat non proident, sunt in culpa qui officia deserunt  mollit anim id est laborum.\n\n ### Heading 3 \n\n Sed ut perspiciatis unde omnis iste natus error sit voluptatem  accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab  illo inventore veritatis et quasi architecto beatae vitae dicta sunt  explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut  odit aut fugit, sed quia consequuntur magni dolores eos qui ratione  voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum  quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam  eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat  voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam  corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?  Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse  quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo  voluptas nulla pariatur?".trimMargin()))
    }

    for (i in 0..9) {
        items.add(Item(i, "Image name", "admin", "A brief and optional description of this artwork, written by its creator."))
    }

    routing {
        get("/") {
            call.respond(JteContent("pages/about.kte", mapOf()))
        }

        get("/news") {
            call.respond(JteContent("pages/news.kte", mapOf("articles" to articles)))
        }

        get("/news/{article_id}") {
            val id = call.parameters["article_id"]?.toIntOrNull() ?: -1

            articles.getOrNull(id)?.let {
                call.respond(JteContent("pages/article.kte", mapOf("article" to it)))
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        get("/explore") {
            call.respond(JteContent("pages/explore.kte", mapOf("items" to items)))
        }

        get("/explore/{item_id}") {
            val id = call.parameters["item_id"]?.toIntOrNull() ?: -1

            items.getOrNull(id)?.let {
                call.respond(JteContent("pages/item.kte", mapOf("item" to items[id], "svg" to Encoder.encode(SimpleGridGenerator().generate()))))
            } ?: call.respond(HttpStatusCode.NotFound)
        }
    }
}
