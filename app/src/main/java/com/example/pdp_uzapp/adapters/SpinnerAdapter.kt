package com.example.pdp_uzapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.pdp_uzapp.databinding.ItemSpinnerBinding
import com.example.pdp_uzapp.entity.Group
import com.example.pdp_uzapp.entity.Mentor


class SpinnerAdapter(var list: List<Mentor>): BaseAdapter() {
    override fun getCount(): Int  = list.size

    override fun getItem(position: Int): Mentor = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var binding: ItemSpinnerBinding
        if (convertView == null) {
            binding = ItemSpinnerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        } else {
            binding = ItemSpinnerBinding.bind(convertView)
        }

        binding.txt.text = list[position].firstname

        return  binding.root
    }
}