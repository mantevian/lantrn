package xyz.mantevian.lantrn.article

import java.util.Date

data class Article (val id: Int, val name: String, val dateCreated: Date, val description: String, val content: String)