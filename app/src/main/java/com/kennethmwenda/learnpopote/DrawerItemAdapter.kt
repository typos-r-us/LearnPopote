package com.kennethmwenda.learnpopote

import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class DrawerItemAdapter: RecyclerView.Adapter<DrawerItemAdapter.ViewHolder>() {

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        lateinit var drawerItemAdapter:DrawerItemAdapter

        fun ViewHolder(@NonNull itemView: View){
            super.itemView
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            drawerItemAdapter.setSelected(adapterPosition)
        }
    }
    abstract var listener:OnItemSelectedListener
    lateinit var drawerItemList:List<DrawerItemModel>
    abstract var viewTypes:Map<Class<? : DrawerItemModel>, Int>
    abstract var holderFactories:SparseArray<DrawerItemModel>

    fun DrawerAdapter (drawerItemsList:List<DrawerItemModel>){
        this.drawerItemList = drawerItemList
        this.viewTypes = HashMap<>()
        this.holderFactories = SparseArray<>()

        processViewTypes()
    }

    private fun processViewTypes(){
        var type = 0
        //val item:DrawerItemModel
        for (item in drawerItemList){
            if (!viewTypes.containsKey(item.getClass())){
                viewTypes.put(item.getClass(),type)
                holderFactories.put(type,item)
                type++
            }
        }

    }
    fun setSelected(position: Int){
        var newChecked:DrawerItemModel = drawerItemList.get(position)
        if (!newChecked.isSelectable()){
            return
        }
        for (i in drawerItemList.indices){
            val item:DrawerItemModel = drawerItemList[i]
            if (item.isChecked()){
                item.setChecked(false)
                notifyItemChanged(i)
                break
            }
        }
        newChecked.setChecked(true)
        notifyItemChanged(position)

        if (listener !=null){
            listener.onItemSelected(position)
        }
    }
    @JvmName("setListener1")
    fun setListener(listener: OnItemSelectedListener){
        this.listener = listener
    }
    interface OnItemSelectedListener{
        fun onItemSelected(position:Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder:ViewHolder = holderFactories.get(viewType).createViewHolder(parent)
        holder.drawerItemAdapter = this
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        drawerItemList.get(position).bindViewHolder(holder)
    }

    override fun getItemCount(): Int {
        return drawerItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return viewTypes.get(drawerItemList.get(position).getClass())
    }
}