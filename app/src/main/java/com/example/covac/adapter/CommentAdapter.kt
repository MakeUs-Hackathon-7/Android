package com.example.covac.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covac.R
import com.example.covac.data.Comment
import com.example.covac.databinding.CommentItemBinding

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */
class CommentAdapter(val items: List<Comment>,
                     private val clickListener: (comment: Comment) -> Unit) :
        RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.comment_item, parent, false)
        val viewHolder =
                CommentViewHolder(
                        CommentItemBinding.bind(view)
                )

        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])

        }


        return viewHolder
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.comment = items[position]

    }
}