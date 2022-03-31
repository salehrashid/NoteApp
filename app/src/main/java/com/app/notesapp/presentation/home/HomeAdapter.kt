package com.app.notesapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.notesapp.data.local.entity.Notes
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
            mNotes = data
            //untuk ngasih tau bahwa layoutnya memakai data binding
            executePendingBindings()

//            sudah di tambahkan di xml row item baris ke 4
//            tvTitle.text = data.title
//            tvDescription.text = data.desc
//            tvDate.text = data.date

//            sudah ditambahkan di BindingAdapter.kt dan row item
//            when (data.priority) {
//                Priority.HIGH -> priorityIndicator.setCardBackgroundColor(
//                    priorityIndicator.context.getColor(
//                        R.color.red
//                    )
//                )
//                Priority.MEDIUM -> priorityIndicator.setCardBackgroundColor(
//                    priorityIndicator.context.getColor(
//                        R.color.yellow
//                    )
//                )
//                Priority.LOW -> priorityIndicator.setCardBackgroundColor(
//                    priorityIndicator.context.getColor(
//                        R.color.green
//                    )
//                )
//            }
        }
    }

    override fun getItemCount(): Int = listNotes.size

    fun setData(data: List<Notes>){
        if (data == null) return
        val diffCallback = DiffCallback(listNotes, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listNotes.clear()
        listNotes.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}