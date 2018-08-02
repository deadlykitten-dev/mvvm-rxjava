package com.kestrel9.android.mvvmrxjava

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * MvvmRxjava
 * Class: ListAdapter
 * Created by kestr on 2018-08-02.
 *
 * Description:
 */

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>(){

    private val clickSubject = PublishSubject.create<String>()

    private val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7",
            "Item 8", "Item 9", "Item 10")

    val clickEvent: Observable<String> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var itemText = itemView.findViewById(R.id.tv_item_text) as TextView

        init {
            itemView.setOnClickListener{
                clickSubject.onNext(items[layoutPosition])
            }
        }

        fun bind(text: String){
            itemText.text = text
        }
    }
}