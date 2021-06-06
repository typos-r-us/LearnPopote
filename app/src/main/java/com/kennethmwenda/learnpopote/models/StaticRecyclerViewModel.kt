package com.kennethmwenda.learnpopote.models

class StaticRecyclerViewModel constructor(private val img:Int, private val txt:String) {
    fun getImage(): Int{
        return img
    }
    fun getText():String{
        return txt
    }
}