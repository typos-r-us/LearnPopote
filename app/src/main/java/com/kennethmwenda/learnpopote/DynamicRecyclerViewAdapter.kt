package com.kennethmwenda.learnpopote

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennethmwenda.learnpopote.dynamicRVinterface.LoadMore

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val progressBar = itemView.findViewById<ProgressBar>(R.id.pb_dynamicRv)//.findViewById(R.id.pb_dynamicRv)
}
class  ItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val tvItemName:TextView = itemView.findViewById(R.id.tv_dynamicRvItemName)
}

class DynamicRecyclerViewAdapter(
        val recyclerView: RecyclerView,
        val activity: Activity,
        val itemsList: ArrayList<DynamicRecyclerViewModel?>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // declare and initialize the variables needed
    val VIEW_TYPE_ITEM_COUNT:Int= 0
    val VIEW_TYPE_LOADING:Int= 1
    lateinit var loadMore:LoadMore
    var isLoading:Boolean = false
    var visibleThreshold:Int = 5
    var lastVisibleItem:Int = 10
    var totalItemsCount:Int = 0

    val linearLayoutManager:LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
    fun onScrollListener(@NonNull listener:RecyclerView.OnScrollListener):Unit{
        @Override
        fun onScrolled(@NonNull recyclerView: RecyclerView, dx:Int, dy:Int){
            //super.onScrolled(recyclerView,dx,dy)
            totalItemsCount = linearLayoutManager.itemCount
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
            if (!isLoading && totalItemsCount<=lastVisibleItem+visibleThreshold){
                if (loadMore!=null){
                    loadMore.onLoadMore()
                }
                isLoading = true
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemsList.get(position)==null){
            VIEW_TYPE_LOADING
        }
        else VIEW_TYPE_ITEM_COUNT
        // return 1??
    }

    @JvmName("setLoadMore1")
    fun setLoadMore(loadMore: LoadMore){
        this.loadMore=loadMore
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var view: View
        if (viewType == VIEW_TYPE_ITEM_COUNT) run {
            view = LayoutInflater.from(activity).inflate(R.layout.dynamic_recycler_view_item_layout, parent, false)
            // return LoadingViewHolder(view)
        }else if (viewType == VIEW_TYPE_LOADING) run {
            view = LayoutInflater.from(activity).inflate(R.layout.dynamic_recycler_view_item_layout, parent, false)
            //return LoadingViewHolder(view)
        }
        return LoadingViewHolder(view)
    } // What is this error?? | Took view out of curly braces, declared as lateinit

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ItemViewHolder ){
            val item: DynamicRecyclerViewModel? = itemsList[position]
            val viewHolder:ItemViewHolder = holder
            viewHolder.tvItemName.text = itemsList[position]?.getName() ?: ""
        }
        else if (holder is LoadingViewHolder){
            val loadingViewHolder:LoadingViewHolder = holder
        }
    }

    override fun getItemCount(): Int {

        return itemsList.size
    }

    fun setLoaded(){
        isLoading = false
    }
}