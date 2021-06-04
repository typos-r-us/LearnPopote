package com.kennethmwenda.learnpopote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DynamicRecyclerViewAdapter : RecyclerView.Adapter<DynamicRecyclerViewAdapter.DynamicRecycleViewHolder>{
    var dynamicRVModelsList: ArrayList<DynamicRecyclerViewModel> // Check this for errors

    constructor(dynamicRVModelsList: ArrayList<DynamicRecyclerViewModel>) : super(){
        this.dynamicRVModelsList = dynamicRVModelsList
    }


    class DynamicRecycleViewHolder : RecyclerView.ViewHolder{
        var imageView:ImageView
        var textView:TextView
        var constraintLayout:ConstraintLayout

        constructor(itemView: View) : super(itemView){
            imageView = itemView.findViewById(R.id.iv_dynamicRvImage)
            textView = itemView.findViewById(R.id.tv_dynamicRvItemName)
            constraintLayout = itemView.findViewById(R.id.cl_dynamicRvCLayout)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DynamicRecyclerViewAdapter.DynamicRecycleViewHolder {
        // inflate the layout
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.dynamic_recycler_view_item_layout,parent,false)
        val dynamicRvHolder:DynamicRecycleViewHolder = DynamicRecycleViewHolder(view)
        return dynamicRvHolder
    }

    override fun onBindViewHolder(
        holder: DynamicRecyclerViewAdapter.DynamicRecycleViewHolder,
        position: Int
    ) {
        val currentItem:DynamicRecyclerViewModel = dynamicRVModelsList.get(position)
        holder.imageView.setImageResource(currentItem.getImage())
        holder.textView.text = currentItem.getName()
    }

    override fun getItemCount(): Int {
        return dynamicRVModelsList.size
    }

}