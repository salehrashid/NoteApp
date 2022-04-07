package com.app.notesapp.utils

import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.app.notesapp.R
import com.app.notesapp.data.local.entity.Notes
import com.app.notesapp.data.local.entity.Priority

object HelperFunction {
    fun setPriorityColor(context: Context, cardView: CardView): AdapterView.OnItemSelectedListener {
        val listener = object : AdapterView.OnItemSelectedListener {

            val lightRed = ContextCompat.getColor(context, R.color.light_red)
            val yellow = ContextCompat.getColor(context, R.color.yellow)
            val lightBlue = ContextCompat.getColor(context, R.color.light_blue)

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> cardView.setCardBackgroundColor(lightRed)
                    1 -> cardView.setCardBackgroundColor(yellow)
                    2 -> cardView.setCardBackgroundColor(lightBlue)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        return listener
    }

    fun parseToPriority(context: Context?, priority: String): Priority {
        val arrayPriority = context?.resources?.getStringArray(R.array.priorities)
        return when (priority) {
            arrayPriority?.get(0) -> Priority.HIGH
            arrayPriority?.get(1) -> Priority.MEDIUM
            arrayPriority?.get(2) -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkDataIsEmpty(data: List<Notes>){
        emptyDatabase.value = data.isEmpty()
    }
}