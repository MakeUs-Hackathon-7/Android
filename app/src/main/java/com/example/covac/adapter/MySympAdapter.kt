package com.example.covac.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covac.R
import com.example.covac.data.MySymp
import com.example.covac.databinding.MySympItemBinding

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */
class MySympAdapter(val items: List<MySymp>,
                    private val clickListener: (mySymp: MySymp) -> Unit) :
        RecyclerView.Adapter<MySympAdapter.MySympViewHolder>() {
    class MySympViewHolder(val binding: MySympItemBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySympViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_symp_item, parent, false)
        val viewHolder =
                MySympViewHolder(
                        MySympItemBinding.bind(view)
                )

        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])

        }


        return viewHolder
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: MySympViewHolder, position: Int) {
        holder.binding.mysymp = items[position]

    }
}