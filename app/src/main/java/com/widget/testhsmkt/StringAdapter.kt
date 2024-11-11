package com.widget.testhsmkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.widget.testhsmkt.StringAdapter.StringViewHolder

// StringAdapter.java
class StringAdapter(private val stringList: MutableList<String>) :
    RecyclerView.Adapter<StringViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_string, parent, false)
        return StringViewHolder(view)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.textView.text = stringList[position]
    }

    override fun getItemCount(): Int {
        return stringList.size
    }

    fun addString(newString: String) {
        stringList.add(newString)
        notifyItemInserted(stringList.size - 1)
    }

    fun removeString(position: Int) {
        stringList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, stringList.size)
    }

    class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView as TextView
    }
}
