package com.example.myformdatasiswaapp.ui.detail

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myformdatasiswaapp.R
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()
        observeDeleteSuccess()
    }

    private fun initComponent() {
        if (arguments != null){
            val dataSchool = arguments?.getParcelable("dataSchool" ) as? SchoolEntity

            viewModel.getDetailSchool(dataSchool?.dataId ?: 0)

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.detailSchool.collect {
                    if (it?.image != null){
                        val bm = BitmapFactory.decodeByteArray(it.image,0, it.image.size ?: 0)
                        binding.imgSchool.setImageBitmap(bm)
                    }

                    binding.apply {
                        tvSchoolName.text = it?.schoolName
                        tvSchoolEmail.text = it?.schoolEmail
                        tvFacebook.text = it?.schoolFacebook
                        tvPhoneNumber.text = it?.schoolNoHp
                        tvTotalStudent.text = "Total Siswa ${it?.schoolTotalStudent}"
                        tvSchoolAddress.text = "${it?.schoolAddress}, ${it?.schoolProvince}, ${it?.schoolCityDistrict}, ${it?.schoolZipCode}"
                    }
                }
            }

            binding.btnUpdateAndMoveToTable.setOnClickListener {
                val bundle = bundleOf("updateData" to dataSchool)
                findNavController().navigate(R.id.action_detailFragment_to_formFragment, bundle)
            }

            binding.btnDeleteSchool.setOnClickListener {
                dataSchool?.let { it1 ->
                    viewModel.deleteSchool(it1)
                }
            }
        }

        binding.btClose.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun observeDeleteSuccess(){
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.deleteSchoolSuccess.collect {
                if (it == true) findNavController().popBackStack()
            }
        }
    }

}