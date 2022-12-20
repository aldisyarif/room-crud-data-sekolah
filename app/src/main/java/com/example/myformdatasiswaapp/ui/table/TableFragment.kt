package com.example.myformdatasiswaapp.ui.table

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myformdatasiswaapp.R
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.databinding.FragmentTableBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment() {

    private val viewModel: TableViewModel by viewModels()

    private lateinit var adapter: SchoolAdapter

    private lateinit var binding: FragmentTableBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTableBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListSchool()
        observeSchools()

        binding.btnAddData.setOnClickListener {
            findNavController().navigate(R.id.action_tableFragment_to_formFragment)
        }
    }

    private fun observeSchools() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.listSchool.collect {
                if (!it.isNullOrEmpty()){
                    Log.d("TableFragment", "observeSchools: $it")
                    showListSchools(it)
                }
            }
        }
    }

    private fun showListSchools(list: List<SchoolEntity>) {
        binding.apply {
            adapter = SchoolAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }

        adapter.setOnItemClickCallback(object : SchoolAdapter.OnItemClickCallback {
            override fun onItemClicked(school: SchoolEntity) {
                val bundle = bundleOf("dataSchool" to school)
                findNavController().navigate(R.id.action_tableFragment_to_detailFragment, bundle)
            }
        })
    }
}