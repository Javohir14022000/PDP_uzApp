package com.example.pdp_uzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdp_uzapp.databinding.ItemMentorBinding
import com.example.pdp_uzapp.databinding.ItemStudentBinding
import com.example.pdp_uzapp.entity.Student

class RecAdapter2(var list: List<Student>, var listener: OnItemClickListener) :
    RecyclerView.Adapter<RecAdapter2.Vh>() {
    inner class Vh(var itemMentorBinding: ItemMentorBinding) :
        RecyclerView.ViewHolder(itemMentorBinding.root) {
        fun onBind(student: Student, position: Int) {
            itemMentorBinding.apply {
                name.text = list[position].firstName
                surname.text = list[position].lastname
                patron.text = list[position].patron

                edit.setOnClickListener {
                    listener.editClick(student, position)
                }

                delete.setOnClickListener {
                    listener.deleteClick(student, position)
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

    interface OnItemClickListener {
        fun editClick(student: Student, position: Int)
        fun deleteClick(student: Student, position: Int)
    }
}