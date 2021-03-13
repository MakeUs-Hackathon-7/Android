package com.example.covac.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covac.R
import com.example.covac.data.Symptom
import com.example.covac.databinding.SymptomItemBinding
import kotlinx.android.synthetic.main.symptom_item.view.*

/**
 * @author Taeeun Kim
 * @email xodms8713@gmail.com
 * @created 2021-03-13
 */

class SymptomAdapter(val items: List<Symptom>,
                     private val clickListener: (symptom: Symptom) -> Unit) :
    RecyclerView.Adapter<SymptomAdapter.SymptomViewHolder>(){
    class SymptomViewHolder(val binding: SymptomItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.symptom_item, parent, false)
        val viewHolder =
            SymptomViewHolder(
                SymptomItemBinding.bind(view)
            )

        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])

        }


        return viewHolder
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: SymptomViewHolder, position: Int) {
        holder.binding.symptom = items[position]
        
        if (!items[position].sym_1){
            holder.binding.symptom1.visibility=View.GONE
        }

        if (!items[position].sym_2){
            holder.binding.symptom2.visibility=View.GONE
        }

        if (!items[position].sym_3){
            holder.binding.symptom3.visibility=View.GONE
        }

        if (!items[position].sym_4){
            holder.binding.symptom4.visibility=View.GONE
        }

        if (!items[position].sym_5){
            holder.binding.symptom5.visibility=View.GONE
        }
    }

}