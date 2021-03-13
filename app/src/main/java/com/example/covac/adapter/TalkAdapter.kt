package com.example.covac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covac.R

import com.example.covac.data.Talk
import com.example.covac.databinding.TalkItemBinding

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */
class TalkAdapter(val items: List<Talk>,
                  private val clickListener: (talk: Talk) -> Unit) :
        RecyclerView.Adapter<TalkAdapter.TalkViewHolder>() {
    class TalkViewHolder(val binding: TalkItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TalkViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.talk_item, parent, false)
        val viewHolder =
                TalkViewHolder(
                        TalkItemBinding.bind(view)
                )

        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])

        }


        return viewHolder
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: TalkViewHolder, position: Int) {
        holder.binding.talk = items[position]

    }
}