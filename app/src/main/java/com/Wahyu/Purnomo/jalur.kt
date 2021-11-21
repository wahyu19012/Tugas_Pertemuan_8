package com.Wahyu.Purnomo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.selection.SelectionTracker

class jalur(private val listItems:List<Orang>,
            private val context: Context)
    : RecyclerView.Adapter<pegangan>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int = listItems.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): pegangan = pegangan(
            LayoutInflater.from(context)
                    .inflate(R.layout.list_item, parent, false)
    )

    override fun onBindViewHolder(vh: pegangan, position: Int) {
        vh.name.text = listItems[position].name
        vh.phone.text = listItems[position].phone

        val parent = vh.name.parent as LinearLayout

        if(tracker!!.isSelected(position.toLong())) {
            parent.background = ColorDrawable(
                    Color.parseColor("#80deea")
            )
        } else {
            parent.background = ColorDrawable(Color.WHITE)
        }
    }

    private var tracker: SelectionTracker<Long>? = null

    fun setTracker(tracker: SelectionTracker<Long>?) {
        this.tracker = tracker
    }
}