package com.app.notesapp.utils

import android.view.View
import androidx.appcompat.widget.AppCompatSpinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.app.notesapp.R
import com.app.notesapp.data.local.entity.Notes
import com.app.notesapp.data.local.entity.Priority
import com.app.notesapp.presentation.home.HomeFragrmentDirections
import com.google.android.material.card.MaterialCardView

object BindingAdapter {

    @BindingAdapter("android:parsePriorityColor")
    @JvmStatic
    fun parsePriorityColor(cardView: MaterialCardView, priority: Priority) {
        when (priority) {
            Priority.HIGH -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.light_red))
            Priority.MEDIUM -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
            Priority.LOW -> cardView.setCardBackgroundColor(cardView.context.getColor(R.color.light_blue))
        }
    }

    @BindingAdapter("android:sendDataToDetail")
    @JvmStatic
    fun sendDataToDetail(view: ConstraintLayout, currentItem: Notes){
        view.setOnClickListener {
            val action = HomeFragrmentDirections.actionHomeFragrmentToDetailFragment2(currentItem)
            view.findNavController().navigate(action)
        }
    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: AppCompatSpinner, priority: Priority) {
        when (priority) {
            Priority.HIGH -> {
                view.setSelection(0)
            }
            Priority.MEDIUM -> {
                view.setSelection(1)
            }
            Priority.LOW -> {
                view.setSelection(2)
            }
        }
    }

    //yoenas
    @BindingAdapter("android:emptyDatabase")
    @JvmStatic
    fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
        when(emptyDatabase.value){
            true -> view.visibility = View.VISIBLE
            else -> view.visibility = View.INVISIBLE
        }
    }
}