package com.example.pdp_uzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdp_uzapp.databinding.ItemMoreUseBinding

import com.example.pdp_uzapp.entity.Course

class RecAdapter(var list: List<Course>, var listener: OnClickMyListener):RecyclerView.Adapter<RecAdapter.VH>() {

    inner class VH(var binding: ItemMoreUseBinding): RecyclerView.ViewHolder(binding.root)

    interface OnClickMyListener {
        fun onClick(course: Course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMoreUseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            courseName.text = list[position].name

            root.setOnClickListener {
                listener.onClick(list[position])
            }
        }
    }

    override fun getItemCount(): Int  = list.size
}