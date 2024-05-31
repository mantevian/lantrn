package xyz.mantevian.lantrn.svg

abstract class SvgNode(val name: String) {

    val children: MutableList<SvgNode> = mutableListOf()
    val attributes: MutableMap<String, Any> = mutableMapOf()

    var id: String? = null
        set(value) {
            set("id", value)
            field = value
        }

    fun set(name: String, value: Any?) {
        value?.let { attributes[name] = value }
    }

    fun addChild(child: SvgNode) {
        children.add(child)
    }
}