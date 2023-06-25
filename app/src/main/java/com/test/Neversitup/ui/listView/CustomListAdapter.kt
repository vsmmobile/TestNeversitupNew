package com.test.Neversitup.ui.listView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


import com.test.Neversitup.R
import com.test.Neversitup.database.DbModel
import or.th.mobile.librarymobile.common.GobalFun

class CustomListAdapter(context: Context, private val items: List<DbModel.CurrentpriceDetail>) : ArrayAdapter<DbModel.CurrentpriceDetail>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        val viewHolder: ViewHolder

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false)
            viewHolder = ViewHolder()
            viewHolder.imageView = itemView.findViewById(R.id.image_view)
            viewHolder.dateTimeTextView = itemView.findViewById(R.id.textviewDateTime)
            viewHolder.USDTextView = itemView.findViewById(R.id.textviewUSD)
            viewHolder.GBPTextView = itemView.findViewById(R.id.textviewGBP)
            viewHolder.EURTextView = itemView.findViewById(R.id.textviewEUR)
            itemView.tag = viewHolder
        } else {
            viewHolder = itemView.tag as ViewHolder
        }
        val item = items[position]
        viewHolder.dateTimeTextView.text =GobalFun.dateTimeshow(item.create_date)
        viewHolder.USDTextView.text = item.USD
        viewHolder.GBPTextView.text =  item.GBP
        viewHolder.EURTextView.text =item.EUR
        return itemView!!
    }

    private class ViewHolder {
        lateinit var imageView:ImageView
        lateinit var dateTimeTextView: TextView
        lateinit var USDTextView: TextView
        lateinit var GBPTextView: TextView
        lateinit var EURTextView: TextView
    }
}


