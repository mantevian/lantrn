package xyz.mantevian.lantrn.article

data class Article(
	val id: Int,
	val name: String,
	val dateCreated: String,
	val description: String,
	val imageUri: String,
	val content: String
)