package com.app.notesapp.presentation.detail

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.notesapp.R
import com.app.notesapp.databinding.FragmentDetailBinding
import com.app.notesapp.presentation.NotesViewModel
import com.app.notesapp.presentation.home.HomeFragrmentDirections
import com.app.notesapp.utils.ExtensionFunctions.setActionBar

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    private val args by navArgs<DetailFragmentArgs>()
    private val detailViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.toolbarDetail.setActionBar(requireActivity())

        binding.detail = args

//        binding.apply {
//            tvTitleDetail.text = args.notes.title
//            tvDescriptionDetail.text = args.notes.desc
//            tvDateDetail.text = args.notes.date
//        }

        Log.d("detail", args.notes.title)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit ->{
                val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment2(args.notes)
                findNavController().navigate(action)
            }
            R.id.menu_delete -> confirmDeleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteNote() {
        AlertDialog.Builder(context)
            .setTitle("Delete '${args.notes.title}'?")
            .setMessage("Are you sure to delete your '${args.notes.title}' note?")
            .setPositiveButton("Yes") { _, _ ->
                detailViewModel.deleteNote(args.notes)
                Toast.makeText(context, "Successfully to delete your note", Toast.LENGTH_SHORT)
                    .show()
                findNavController().navigate(R.id.action_detailFragment_to_homeFragrment)
            }
            .setNegativeButton("No") { _, _ -> }
//            .setNeutralButton("Cancel") { _, _ -> }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}