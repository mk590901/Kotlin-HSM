// ButtonAdapter.kt

package com.widget.testhsmkt

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.widget.testhsmkt.implementation.Samek_9BContextObject
import com.widget.testhsmkt.support.ObjectEvent

class ButtonAdapter(private val buttonTexts: List<String>, private val contextObject: Samek_9BContextObject) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {
    val TAG: String = "hsm"

    //private val contextObject: Samek_9BContextObject = contextObject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val text = buttonTexts[position].toLowerCase()
        holder.button.text = text
        holder.button.setOnClickListener(View.OnClickListener {
            val event = text
            val eventId: Int = contextObject.getEventId(event)
            Log.d(TAG, "->[$event = $eventId]")
            contextObject.done(ObjectEvent(eventId, event))
        })
    }

    override fun getItemCount(): Int {
        return buttonTexts.size
    }

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView as Button
    }
}

/*
package com.widget.testhsmkt

// ButtonAdapter.java
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.widget.testhsmkt.implementation.Samek_9BContextObject
import com.widget.testhsmkt.support.ObjectEvent
import java.util.Locale

class ButtonAdapter(private val buttonTexts: List<String>, contextObject: Samek_9BContextObject) :
    RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder?>() {
    val TAG: String = "hsm"

    private val contextObject: Samek_9BContextObject = contextObject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_button, parent, false)
        return ButtonViewHolder(view)
    }

//    override fun getItemCount(): Int {
//        return buttonTexts.size;
//    }

    override fun getItemCount(): Int {
        return buttonTexts.size
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val text = buttonTexts[position]
        val event = text.lowercase(Locale.getDefault())
        val eventId: Int = contextObject.getEventId(event)
        holder.button.text = text
        holder.button.setOnClickListener { v: View? ->
            Log.d(TAG, "->[$event = $eventId]")
            contextObject.done(ObjectEvent(eventId, event))
        }
    }

    val itemCount: Int
        get() = buttonTexts.size

    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var button: Button = itemView as Button
    }
}
*/