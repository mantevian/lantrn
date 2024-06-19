package xyz.mantevian.lantrn.plugins

import gg.jte.TemplateEngine
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.jte.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import xyz.mantevian.lantrn.article.Article
import xyz.mantevian.lantrn.generator.FirefliesGenerator
import xyz.mantevian.lantrn.generator.SimpleGridGenerator
import xyz.mantevian.lantrn.generator.SnakeGenerator
import xyz.mantevian.lantrn.generator.WavesGenerator
import xyz.mantevian.lantrn.item.Item
import xyz.mantevian.lantrn.svg.Encoder
import java.net.URLEncoder

fun Application.configureTemplating() {
	install(Jte) {
		templateEngine = TemplateEngine.createPrecompiled(gg.jte.ContentType.Html)
	}

	val articles = mutableListOf<Article>()
	val items = mutableListOf<Item>()

	articles.apply {

		add(
			Article(
				1,
				"Development started",
				"01 March 2024",
				"Alpha version of Lantrn is in development",
				"/generate?type=simple_grid&seed=999&n=5&raw",
				"## Welcome!\nThis is the start of the development feed of Lantrn.\n\nAlthough I've been pondering about the idea of centralizing all my computational art desires in a single large project for several years, first steps towards implementing this dream of mine came to live just a few months ago. The designing part of the project finally took off in spring of 2024, and here we are. I've laid out what you need to know about this project on the main page. More posts are about to come, so look out for them!"
			)
		)

		add(
			Article(
				2,
				"First patterns added",
				"08 June 2024",
				"You can now use Simple Grid, Fireflies and Waves",
				"/generate?type=fireflies&seed=555&n=5&raw",
				"## Not much for now\nBut it's honest work. This website finally came to a somewhat working state, and you can even check out the Demo page to try out the generators, albeit quite simple for now, but it's a proof of concept. With all the technical ground established, I can now move quicker to create something more spectacular than what we have.\n### New patterns added\n* Simple Grid. This one was the testing ground, but I decided to release it anyway. It generates just squares and triangles, but there's definitely much much more that can be done in a square grid.\n* Fireflies. My jaw dropped when I saw this all, the gradients, the lines, the fireflies, which I mocked up in Inkscape in a couple of minutes. I need to do something more spectacular with fireflies though. Kinda fits the concept of Lantrn.\n* Waves. This is what Fireflies was planned to be initially, but I messed up the fills and applied stroke accidentally, which resulted in those background waves in Fireflies. Anyhow, I'm glad both of them came to life separately.\n\nThat is it for now. Some time soon I really want to explore a technique called Wave Function Collapse which is used for procedural generation in games."
			)
		)

		add(
			Article(
				3,
				"First alpha launch",
				"14 June 2024",
				"Launched live at lantrn.mantevian.xyz",
				"/generate?type=waves&seed=1&n=8&raw",
				"## WE ARE FULL LIVE!\nI've now got a full VPS, Virtual Private Server, to host my personal projects on, including Lantrn. As I've had this domain for over a year now, it just made sense to use it for this project. I should probably document how I set it up, because it was a quite tedious but rewarding process.\n\n### VPS\nA Virtual Private Server is kind of a cloud container for an operating system that you get the full control of without having to have the server physically. One of the pre-installed options I had was Debian 12, and I went for it, not wanting to set up a whole OS by myself remotely (I'm not a low-level guy, setting up Ubuntu on my laptop was enough). My experience can be described concisely: wow, I really need to install sudo.\n\nAfter installing Java and throwing my nicely built .jar on there, I got it working on a numerical HTTP address. There was a problem, two of them actually.\n### nginx\nI wanted this server to host multiple projects of mine on different subdomains of mantevian.xyz. I can launch my applications at different ports, but because DNS records don't differentiate ports out of the box, I had to set up a reverse proxy to handle incoming requests at, for example, lantrn.mantevian.xyz with the default port 80 (well, 443 for a secure connection), and assign them to a specified port of a corresponding application. This can be done with a popular utility called nginx which is pretty easy to get working.\n### HTTPS\nThe biggest challenge for me was just setting up an SSL certificate to use HTTPS like any normal human being on this planet. There are a few steps for this:\n* Install certbot, a utility to make the magic happen.\n* Configure certbot to verify the SSL certificate using a DNS record in order to use a custom domain name for the server.\n* Start up certbot and follow the instructions. You'll need to create a specific DNS record to prove your ownership of the domain name.\n* After the DNS record has been deployed which might take some time, you'll get an SSL certificate created, which you can use to configure nginx.\n\nThis seems pretty simple post-factum, the challenge was rather assembling all this information together. After all the steps above, I got Lantrn working on this fancy domain. I think this is actually a good idea to write these small guides down for maybe me in the future or someone else who stumbles upon this."
			)
		)

		add(
			Article(
				4,
				"First alpha testing",
				"18 June 2024",
				"A live testing session with several users. This is some UX talk now",
				"/generate?type=snake&n=500&seed=5774250&raw",
				"## Time to play serious\nSince Lantrn is actually starting off as a university project, this is mandatory to do, but I can see how testing with several people can be helpful to improve the website and make the idea more understandable for more people, because I still need to learn how to explain it to people and make them get it first try.\n### How it works\nAnyway, if all goes according to plan, I'll have 5 people who I'll be watching exploring the website live and interviewing them about their experience. My goal is to give my testers a tour of the entire website while not making it take too long. The point of quick testing is that if while doing it the user struggles with something, it's an indicator of something being wrong with my design. It doesn't even have to be verbal feedback from the interviewee, I should be able to see it with my eyes in the way they navigate around the website.\n### The questionaire, sort of\nMy planned process consists of the following 5 steps:\n1. You open the website for the first time. What do you see? Which parts does the website consist of? Can you tell what is the goal of this project?\n2. Would you like to read some news? Well, there is a chance they might notice this plan and know what is about to come, but that's probably unlikely.\n3. Let's now open the Explore tab and see what's there. This is to let people know what the capabilities of Lantrn are. Explore will contain the best of the best. There is also an option to download those images.\n4. The most fun part, I hope, is the Demo section. Let's try to mess with parameters and generate something! You can also download your newly generated image as SVG (more formats coming later).\n5. The most advanced part and the one I'm most curious about the UX of. When you generate an image, the current page's URL actually gets populated with the query of your generation. I'm quite unsure of this model because as this project grows, that query might just become too long to be handled by URL or to be used to share with people. Anyway, what I want you to do is to copy the link and open it in a new tab in your browser. So this URL works both ways, which is convenient to share the exact settings with someone.\n### Final words\nThat's it for the current stage of development. The design you see right now could very well be completely remade just because I would feel like it. But I encourage you to reach out and come back whenever to see the progress, play around with the generator more, or just come to grab a fancy background wallpaper, whatever works."
			)
		)

	}

	items.apply {
		add(
			Item(
				1,
				"Simple Grid 1",
				"mantevian",
				"N = 6, Seed = 123",
				"type=simple_grid&seed=123&n=6"
			)
		)

		add(
			Item(
				2,
				"Simple Grid 2",
				"mantevian",
				"N = 12, Seed = 456",
				"type=simple_grid&seed=456&n=12"
			)
		)

		add(
			Item(
				3,
				"Simple Grid 3",
				"mantevian",
				"N = 18, Seed = 789",
				"type=simple_grid&seed=789&n=18"
			)
		)

		add(
			Item(
				4,
				"Fireflies 1",
				"mantevian",
				"N = 5, Seed = 12",
				"type=fireflies&seed=12&n=5"
			)
		)

		add(
			Item(
				5,
				"Fireflies 2",
				"mantevian",
				"N = 5, Seed = 34",
				"type=fireflies&seed=34&n=5"
			)
		)

		add(
			Item(
				6,
				"Fireflies 3",
				"mantevian",
				"N = 5, Seed = 56",
				"type=fireflies&seed=56&n=5"
			)
		)

		add(
			Item(
				7,
				"Waves 1",
				"mantevian",
				"N = 6, Seed = 7478970",
				"type=waves&seed=7478970&n=5"
			)
		)

		add(
			Item(
				8,
				"Waves 2",
				"mantevian",
				"N = 7, Seed = 5430319",
				"type=waves&seed=5430319&n=5"
			)
		)

		add(
			Item(
				9,
				"Waves 3",
				"mantevian",
				"N = 8, Seed = 205970",
				"type=waves&seed=205970&n=5"
			)
		)

		add(
			Item(
				10,
				"Snake 1",
				"mantevian",
				"N = 500, Seed = 21947",
				"type=snake&n=500&seed=21947"
			)
		)

		add(
			Item(
				11,
				"Snake 2",
				"mantevian",
				"N = 500, Seed = 453969",
				"type=snake&n=500&seed=453969"
			)
		)

		add(
			Item(
				12,
				"Snake 3",
				"mantevian",
				"N = 2500, Seed = 2945895",
				"type=snake&n=2500&seed=2945895"
			)
		)
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

			articles.find { it.id == id }?.let {
				call.respond(JteContent("pages/article.kte", mapOf("article" to it)))
			} ?: call.respond(HttpStatusCode.NotFound)
		}

		get("/explore") {
			call.respond(JteContent("pages/explore.kte", mapOf("items" to items)))
		}

		get("/explore/{item_id}") {
			val id = call.parameters["item_id"]?.toIntOrNull() ?: -1

			items.find { it.id == id }?.let {
				call.respond(
					JteContent(
						"pages/item.kte",
						mapOf(
							"item" to it,
						)
					)
				)
			} ?: call.respond(HttpStatusCode.NotFound)
		}

		get("/demo") {
			call.respond(JteContent("pages/demo.kte", mapOf()))
		}

		get("/generate") {
			call.parameters["blank"]?.let {
				call.respond(Encoder.encode(SimpleGridGenerator(seed = 0).generate(5)))
			}

			val type = call.parameters["type"] ?: "simple_grid"
			val n = call.parameters["n"]?.toIntOrNull() ?: 5
			val seed = call.parameters["seed"]?.toIntOrNull() ?: 0

			val svg: String = Encoder.encode(
				when (type) {
					"simple_grid" -> SimpleGridGenerator(seed).generate(n)
					"fireflies" -> FirefliesGenerator(seed).generate(n)
					"waves" -> WavesGenerator(seed).generate(n)
					"snake" -> SnakeGenerator(seed).generate(n)
					else -> SimpleGridGenerator(seed).generate(n)
				}
			)

			call.parameters["raw"]?.let {
				call.respond("<img src=\"data:image/svg+xml,${URLEncoder.encode(svg, "utf-8").replace("+", "%20")}\">")
			} ?: call.respond(svg)
		}
	}
}
