package com.app.notesapp.presentation.add

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.notesapp.R
import com.app.notesapp.data.local.entity.Notes
import com.app.notesapp.databinding.FragmentAddBinding
import com.app.notesapp.presentation.NotesViewModel
import com.app.notesapp.utils.ExtensionFunctions.setActionBar
import com.app.notesapp.utils.HelperFunction.parseToPriority
import com.app.notesapp.utils.HelperFunction.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding
    private val addViewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.apply {
            toolbarAdd.setActionBar(requireActivity())
            spinnerPriorities.onItemSelectedListener =
                context?.let { setPriorityColor(it, priorityIndicator) }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNote()

        }
    }

    private fun insertNote() {
        binding.apply {
            val title = edtTitle.text.toString()
            val desc = edtDescription.text.toString()

            val calendar = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calendar)
            val priority = spinnerPriorities.selectedItem.toString()

            if (edtTitle.text.isEmpty() || edtDescription.text.isEmpty()) {
                when {
                    edtTitle.text.isEmpty() -> {
                        edtTitle.error = "Please fill the field"
                    }
                    edtDescription.text.isEmpty() -> {
                        edtDescription.error = "Please fill the field"
                    }
                    else -> {
                        edtTitle.error = "Please fill the field"
                        edtDescription.error = "Please fill the field"
                    }
                }

            } else {
                val data = Notes(
                    0,
                    title,
                    desc,
                    date,
                    parseToPriority(context, priority)
                )
                addViewModel.insertNotes(data)
                Log.d("AddFragment", "insertNote $data")
                findNavController().navigate(R.id.action_addFragment_to_homeFragrment)
                Toast.makeText(context, "Successfully Add Note", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.menu_save ->{
//                findNavController().navigate(R.id.action_addFragment_to_homeFragrment)
//                Toast.makeText(context, "Successfully Add Note", Toast.LENGTH_LONG).show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}