package com.example.retrofit

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_row.view.*
import kotlinx.android.synthetic.main.comment_row.view.*

//Pass the ArrayList and a listener, and add a variable to hold your data//

class MyAdapter (private val commentList : ArrayList<comments>, private val listener :

//Extend RecyclerView.Adapter//

Listener) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(comment : comments)

    }

//Define an array of colours//

    private val colors : Array<String> = arrayOf("#7E57C2", "#42A5F5", "#26C6DA", "#66BB6A", "#FFEE58", "#FF7043" , "#EC407A" , "#d32f2f")

//Bind the ViewHolder//

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//Pass the position where each item should be displayed//

        holder.bind(commentList[position], listener, colors, position)

    }

//Check how many items you have to display//

    override fun getItemCount(): Int = commentList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_row, parent, false)
        return ViewHolder(view)

    }

//Create a ViewHolder class for your RecyclerView items//

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

//Assign values from the data model, to their corresponding Views//

        fun bind(comment: comments, listener: Listener, colors : Array<String>, position: Int) {

//Listen for user input events//

            itemView.setOnClickListener{ listener.onItemClick(comment) }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.comment_body.text = comment.body
            itemView.comment_email.text = comment.email
            itemView.comment_name.text= comment.name

        }

    }

}