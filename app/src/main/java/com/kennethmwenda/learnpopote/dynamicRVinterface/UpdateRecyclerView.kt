package com.kennethmwenda.learnpopote.dynamicRVinterface

import com.kennethmwenda.learnpopote.models.DynamicRecyclerViewModel

interface UpdateRecyclerView {
    fun callback(position: Int, itemsList: ArrayList<DynamicRecyclerViewModel>) {
    }
}