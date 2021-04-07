package com.kennethmwenda.learnpopote

import android.view.ViewGroup
import kotlin.properties.Delegates

abstract class DrawerItemModel <T:DrawerItemAdapter.ViewHolder>{
    private var isChecked by Delegates.notNull<Boolean>()
    abstract fun createViewHolder(parent: ViewGroup):T
    abstract fun bindViewHolder(holder: T)

    fun setChecked(isChecked:Boolean):DrawerItemModel<T>{
        this.isChecked = isChecked
        return this
    }

    @JvmName("isChecked1")
    fun isChecked():Boolean{
        return isChecked
    }

    fun isSelectable():Boolean{
        return true
    }
}