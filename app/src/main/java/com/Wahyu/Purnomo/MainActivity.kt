package com.Wahyu.Purnomo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import androidx.recyclerview.selection.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tracker: SelectionTracker<Long>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myList = listOf(
                Orang("Wahyu", "085712860715"),
                Orang("Pur", "082327794328"),
                Orang("yu", "08879879999"),
                Orang("adnan", "076575758787"),
                Orang("195410068", "8789798799009"),
                Orang("ganteng", "98669969788")
        )

        my_rv.layoutManager = LinearLayoutManager(this)
        my_rv.setHasFixedSize(true)

        val adapter = jalur(myList, this)
        my_rv.adapter = adapter

        tracker = SelectionTracker.Builder<Long>(
                "selection-1",
                my_rv,
                StableIdKeyProvider(my_rv),
                lihatdata(my_rv),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything())
         .build()

        if(savedInstanceState != null)
            tracker?.onRestoreInstanceState(savedInstanceState)

        adapter.setTracker(tracker!!)

        tracker?.addObserver(object: SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                val nItems:Int? = tracker?.selection?.size()

                if(nItems!=null && nItems > 0) {
                    title = "$nItems items selected"
                    supportActionBar?.setBackgroundDrawable(
                            ColorDrawable(Color.parseColor("#ef6c00")))
                } else {
                    title = "RVSelection"
                    supportActionBar?.setBackgroundDrawable(
                            ColorDrawable(getColor(R.color.colorPrimary)))
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        if(outState != null)
            tracker?.onSaveInstanceState(outState)
    }
}
