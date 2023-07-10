package com.indicorp.mvvm.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.indicorp.mvvm.Model.Todo
import com.indicorp.mvvm.R

class MyAdapter(private val data: List<Todo>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewUserId: TextView = itemView.findViewById(R.id.textViewUserId)
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewCompleted: TextView = itemView.findViewById(R.id.textViewCompleted)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.your_list_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.textViewUserId.text = "User ID: ${item.userId}"
        holder.textViewId.text = "ID: ${item.Id}"
        holder.textViewTitle.text = item.title
        holder.textViewCompleted.text = "Completed: ${item.completed}"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
