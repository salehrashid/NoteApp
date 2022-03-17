package com.app.notesapp.presentation.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.app.notesapp.MainActivity
import com.app.notesapp.R
import com.app.notesapp.databinding.FragmentHomeBinding
import com.app.notesapp.presentation.NotesViewModel

class HomeFragrment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    private val homeViewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarHome.apply {
            setupWithNavController(navController, appBarConfiguration)
            //requireActivity adalah mengambil activity dari mana?, misal dari main activity dll
            (requireActivity() as MainActivity).setSupportActionBar(this)
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragrment_to_addFragment2)
        }
        binding.btnGoToDetail.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragrment_to_detailFragment2)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
         _binding = null
    }
}