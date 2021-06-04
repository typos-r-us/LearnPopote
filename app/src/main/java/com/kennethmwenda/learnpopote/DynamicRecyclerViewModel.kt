package com.kennethmwenda.learnpopote

class DynamicRecyclerViewModel(val name: String, val image: Int) {

    @JvmName("getName1")
    fun getName():String{
        return name
    }
    @JvmName("getImage1")
    fun getImage():Int{
        return image
    }
}