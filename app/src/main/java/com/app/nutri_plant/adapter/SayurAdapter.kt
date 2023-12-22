package com.app.nutri_plant.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.nutri_plant.databinding.ItemSayurBinding
import com.app.nutri_plant.helper.viewBinding
import com.app.nutri_plant.model.DataSayur

class SayurAdapter (
    private val context: Context,
    var data: List<DataSayur>,
    val cellClickListener: CellClickListener
) :
    RecyclerView.Adapter<SayurAdapter.MyViewHolder>() {

    inner class MyViewHolder(binding: ItemSayurBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var tvTitle = binding.tvTitle
        var imgSayur = binding.imgSayur

        var parentLy = binding.parentLayout

        fun bind(data: DataSayur, pos: Int) {
            tvTitle.text = data.title.toString()

            imgSayur.setImageResource(data.img)

            parentLy.setOnClickListener {
                cellClickListener.selectSayur(data)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SayurAdapter.MyViewHolder {
        return MyViewHolder(parent.viewBinding(ItemSayurBinding::inflate))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = data!![position]
        holder.bind(data, position)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    interface CellClickListener {
        fun selectSayur(data: DataSayur)
    }
}
