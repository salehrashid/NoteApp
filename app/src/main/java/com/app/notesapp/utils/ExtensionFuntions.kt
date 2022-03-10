package com.app.notesapp.utils

import android.content.Context
import android.view.View
import android.widget.AdapterView
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

    fun setPriorityColor(context: Context, cardView: CardView): AdapterView.OnItemSelectedListener {
       val listener = object : AdapterView.OnItemSelectedListener {

            val pink = ContextCompat.getColor(context, R.color.pink)
            val yellow = ContextCompat.getColor(context, R.color.yellow)
            val green = ContextCompat.getColor(context, R.color.green)

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> cardView.setCardBackgroundColor(pink)
                    1 -> cardView.setCardBackgroundColor(yellow)
                    2 -> cardView.setCardBackgroundColor(green)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        return listener
    }
}