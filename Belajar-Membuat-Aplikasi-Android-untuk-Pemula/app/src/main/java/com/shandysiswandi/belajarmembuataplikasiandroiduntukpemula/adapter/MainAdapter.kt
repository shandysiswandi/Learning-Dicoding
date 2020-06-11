package com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.R
import com.shandysiswandi.belajarmembuataplikasiandroiduntukpemula.model.Prophet
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val x = object : DiffUtil.ItemCallback<Prophet>() {
        override fun areItemsTheSame(oldItem: Prophet, newItem: Prophet): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Prophet, newItem: Prophet): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, x)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder =
        MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))

    override fun getItemCount(): Int = differ.currentList.size
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    fun submitList(list: List<Prophet>) {
        differ.submitList(list)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Prophet) = with(itemView) {
            itemView.cv.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.image.setImageResource(item.image)
            itemView.tv_name.text = item.name
            itemView.tv_description.text = item.description
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Prophet)
    }
}