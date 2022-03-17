package com.app.notesapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.notesapp.R
import com.app.notesapp.data.local.entity.Notes
import com.app.notesapp.data.local.entity.Priority
import com.app.notesapp.databinding.RowItemNotesBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = listNotes.get(position)
        holder.binding.apply {
            tvTitle.text = data.title
            tvDescription.text = data.desc
            tvDate.text = data.date

            when (data.priority) {
                Priority.HIGH -> priorityIndicator.setCardBackgroundColor(
                    priorityIndicator.context.getColor(
                        R.color.pink
                    )
                )
                Priority.MEDIUM -> priorityIndicator.setCardBackgroundColor(
                    priorityIndicator.context.getColor(
                        R.color.yellow
                    )
                )
                Priority.LOW -> priorityIndicator.setCardBackgroundColor(
                    priorityIndicator.context.getColor(
                        R.color.green
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(data: List<Notes>){
        if (data == null) return
        listNotes.clear()
        listNotes.addAll(data)
    }
}