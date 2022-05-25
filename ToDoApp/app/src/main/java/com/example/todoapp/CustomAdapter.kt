package com.example.todoapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.*


class CustomAdapter(context: Context?, layoutResourceId: Int, private val mCards: List<ToDoData>) :
    ArrayAdapter<ToDoData>(context!!, layoutResourceId, mCards) {
    override fun getCount(): Int {
        return mCards.size
    }

    override fun getItem(position: Int): ToDoData {
        return mCards[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView != null) {
            viewHolder = convertView.tag as ViewHolder
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_view, null)
            viewHolder = ViewHolder()
            viewHolder.titleTextView =
                convertView.findViewById(R.id.title_text_view)
            viewHolder.contentTextView =
                convertView.findViewById(R.id.content_text_view)
            convertView.tag = viewHolder
        }
        val toDoData = mCards[position]
        viewHolder.titleTextView!!.text = toDoData.title
        viewHolder.contentTextView!!.text = toDoData.content
        return convertView!!
    }

    fun getToDoDataKey(key: String): ToDoData? {
        for (toDoData in mCards) {
            if (toDoData.firebaseKey == key) {
                return toDoData
            }
        }
        return null
    }

    internal class ViewHolder {
        var titleTextView: TextView? = null
        var contentTextView: TextView? = null
    }
}