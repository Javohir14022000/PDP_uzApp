package com.example.pdp_uzapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pdp_uzapp.databinding.ItemGroupPagerBinding
import com.example.pdp_uzapp.entity.Group


class RecAdapter1(var list: List<Group>, var listener: OnClickMyListener) :
    RecyclerView.Adapter<RecAdapter1.VH>() {

    inner class VH(var binding: ItemGroupPagerBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnClickMyListener {
        fun onclickShow(group: Group)
        fun onClickDelete(group: Group, position: Int)
        fun onClickEdit(group: Group, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemGroupPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var group = list.get(position)
        holder.binding.apply {
            courseName.text = list[position].name

//            lastname.text = "o`quvchilar soni: ${dbHelper.getListStudentByID(list[position].id).size}"

//            if (!boolean) {
//                show.visibility = View.GONE
//            }

            edit.setOnClickListener {
                listener.onClickEdit(group, position)
            }

            delete.setOnClickListener {
                listener.onClickDelete(group, position)
            }

            show.setOnClickListener {
                listener.onclickShow(group)
//                listener.onclickShow(list[position], dbHelper.getListStudentByID(list[position].id).size)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}