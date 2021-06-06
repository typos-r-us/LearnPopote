package com.kennethmwenda.learnpopote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.kennethmwenda.learnpopote.R
import com.kennethmwenda.learnpopote.models.CourseItemModel

class CourseItemAdapter(
    var mCtx: Context,
    var resources: Int,
    item1: Int,
    var item: List<CourseItemModel>
): ArrayAdapter<CourseItemModel>(mCtx,resources,item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.img_image1)
        val titleTextView: TextView = view.findViewById(R.id.tv_text1)
        val descriptionTextView: TextView = view.findViewById(R.id.tv_text2)

        var mItem:CourseItemModel = item[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.img))
        titleTextView.text = mItem.title
        descriptionTextView.text = mItem.description

        return view //super.getView(position, convertView, parent)
    }
}