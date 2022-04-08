package com.example.pdp_uzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdp_uzapp.databinding.ItemMentorBinding
import com.example.pdp_uzapp.entity.Mentor

class RecAdapter3(val list: List<Mentor>, var listener: OnItemMentorClickListener) :
    RecyclerView.Adapter<RecAdapter3.Vh>() {
    inner class Vh(var itemMentorBinding: ItemMentorBinding) :
        RecyclerView.ViewHolder(itemMentorBinding.root) {
        fun onBind(mentor: Mentor, position: Int) {
            itemMentorBinding.apply {
                name.text = mentor.firstname
                surname.text = mentor.lastname
                patron.text = mentor.patron

                edit.setOnClickListener {
                    listener.onClickDelete(mentor, position)
                }

                delete.setOnClickListener {
                    listener.onClickDelete(mentor, position)
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemMentorClickListener {
        fun onClickEdit(mentor: Mentor, position: Int)
        fun onClickDelete(mentor: Mentor, position: Int)
    }
}