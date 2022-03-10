package com.app.notesapp.utils

import android.content.Context
import androidx.appcompat.widget.AppCompatSpinner
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.app.notesapp.MainActivity
import com.app.notesapp.R
import com.google.android.material.appbar.MaterialToolbar

object ExtensionFunctions {
    fun MaterialToolbar.setActionBar(activity: FragmentActivity) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(navController, appBarConfiguration)
        (activity as MainActivity).setSupportActionBar(this)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.updateFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.addFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.detailFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
            }
        }
    }

    fun AppCompatSpinner.setPriorityColor(context: Context, cardView: CardView) {
        val arrPriority = resources.getStringArray(R.array.priorities)
        val pink = ContextCompat.getColor(context, R.color.pink)
        val yellow = ContextCompat.getColor(context, R.color.yellow)
        val green = ContextCompat.getColor(context, R.color.green)

        when (this.selectedItem) {
            arrPriority[0] -> cardView.setCardBackgroundColor(pink)
            arrPriority[1] -> cardView.setCardBackgroundColor(yellow)
            arrPriority[2] -> cardView.setCardBackgroundColor(green)
        }
    }
}