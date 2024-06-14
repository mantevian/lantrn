package xyz.mantevian.lantrn.item

import xyz.mantevian.lantrn.ResourceUtil

data class Item(val id: Int, val name: String, val author: String, val description: String) {
	//val svg: String = URLEncoder.encode(ResourceUtil.readFile("/static/gallery/${id}.svg") ?: "", "utf-8")
	val svg = ResourceUtil.readFile("/static/gallery/${id}.svg") ?: ""
}