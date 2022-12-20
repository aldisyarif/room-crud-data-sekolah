package com.example.myformdatasiswaapp.ui.form

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myformdatasiswaapp.R
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsItem
import com.example.myformdatasiswaapp.data.model.ProvinsiItem
import com.example.myformdatasiswaapp.databinding.FragmentFormBinding
import com.example.myformdatasiswaapp.utils.getBackStackData
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class FormFragment : Fragment() {

    private var dataSchool: SchoolEntity? = null
    private var myFile: File? = null
    private var isValidedTypeSchool: Boolean = false

    private val viewModel: FormViewModel by viewModels()

    private lateinit var binding: FragmentFormBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponent()
    }

    private fun initComponent() {
        viewModel.getProvince()


        binding.btClose.setOnClickListener {
            findNavController().popBackStack()
        }

        ifUpdateForm()
        photoResult()
        moveToCameraLayout()
        dropdownSchoolType()
        setValidator()

        observeProvincesIndonesia()
        observeCityOrDistrictIndonesia()

        observeDataAndMoveLayout()
    }

    private fun ifUpdateForm() {
        if (arguments != null){
            dataSchool = arguments?.getParcelable("updateData" ) as? SchoolEntity

            binding.apply {
                title.text = "Update Data"

                if (dataSchool?.image != null){
                    binding.imgDefault.visibility = View.GONE
                    binding.imgResult.visibility = View.VISIBLE


                    val bm = BitmapFactory.decodeByteArray(dataSchool?.image,0, dataSchool?.image?.size ?: 0)
                    binding.imgResult.setImageBitmap(bm)
                }

                etTypeSchool.setText(dataSchool?.schoolType)
                etSchoolName.setText(dataSchool?.schoolName)
                etSchoolAddress.setText(dataSchool?.schoolAddress)
                etProvinces.setText(dataSchool?.schoolProvince)
                etSchoolZipCode.setText(dataSchool?.schoolZipCode)
                etCityDistrict.setText(dataSchool?.schoolCityDistrict)
                etSchoolPhoneNumber.setText(dataSchool?.schoolNoHp)
                etSchoolEmail.setText(dataSchool?.schoolEmail)
                etSchoolFacebook.setText(dataSchool?.schoolFacebook)
                etTotalStudent.setText(dataSchool?.schoolTotalStudent)
            }
        }

    }

    private fun setValidator() {
        binding.apply {

            etSchoolZipCode.doOnTextChanged { text, start, before, count ->
                if (text.isNullOrEmpty()){
                    layoutSchoolZipCode.isErrorEnabled = true
                    layoutSchoolZipCode.error = "kode Pos harus di isi"
                    layoutSchoolZipCode.errorIconDrawable = null
                } else {
                    if (text.length > 5){
                        layoutSchoolZipCode.isErrorEnabled = true
                        layoutSchoolZipCode.error = "kode Pos max 5"
                        layoutSchoolZipCode.errorIconDrawable = null
                    } else {
                        layoutSchoolZipCode.isErrorEnabled = false
                    }
                }
            }

            etSchoolEmail.doOnTextChanged { text, start, before, count ->
                if (text.isNullOrEmpty()){
                    layoutSchoolEmail.isErrorEnabled = true
                    layoutSchoolEmail.error = "Email Sekolah harus di isi"
                    layoutSchoolEmail.errorIconDrawable = null
                } else {
                    if (text.let { it1 -> Patterns.EMAIL_ADDRESS.matcher(it1).matches() }){
                        layoutSchoolEmail.isErrorEnabled = false
                    } else {
                        layoutSchoolEmail.isErrorEnabled = true
                        layoutSchoolEmail.error = "Format email tidak valid2"
                    }
                }
            }

            //whe the button clicked
            btnSaveAndMoveToTable.setOnClickListener {

                if (etTypeSchool.text.isNullOrEmpty()){
                    layoutTypeSchool.isErrorEnabled = true
                    layoutTypeSchool.error = "Pilih Tipe Sekolah"
                    layoutTypeSchool.errorIconDrawable = null

                    etTypeSchool.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutTypeSchool.isErrorEnabled = false
                    }
                }

                if (etSchoolName.text.isNullOrEmpty()){
                    layoutSchoolName.isErrorEnabled = true
                    layoutSchoolName.error = "Nama sekolah harus di isi"
                    layoutSchoolName.errorIconDrawable = null

                    etSchoolName.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutSchoolName.isErrorEnabled = false
                    }
                }

                if (etSchoolAddress.text.isNullOrEmpty()){
                    layoutSchoolAddress.isErrorEnabled = true
                    layoutSchoolAddress.error = "Alamat harus di isi"
                    layoutSchoolAddress.errorIconDrawable = null

                    etSchoolAddress.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutSchoolAddress.isErrorEnabled = false
                    }
                }

                if (etProvinces.text.isNullOrEmpty()){
                    layoutProvinces.isErrorEnabled = true
                    layoutProvinces.error = "Provinsi harus di isi"
                    layoutProvinces.errorIconDrawable = null

                    etProvinces.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutProvinces.isErrorEnabled = false
                    }
                }

                if (etSchoolZipCode.text.isNullOrEmpty()){
                    layoutSchoolZipCode.isErrorEnabled = true
                    layoutSchoolZipCode.error = "kode Pos harus di isi"
                    layoutSchoolZipCode.errorIconDrawable = null
                }

                if (etCityDistrict.text.isNullOrEmpty()){
                    layoutEtCityDistrict.isErrorEnabled = true
                    layoutEtCityDistrict.error = "kota/kabupaten harus di isi"
                    layoutEtCityDistrict.errorIconDrawable = null

                    etCityDistrict.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutEtCityDistrict.isErrorEnabled = false
                    }
                }

                if (etSchoolPhoneNumber.text.isNullOrEmpty()){
                    layoutShoolPhoneNumber.isErrorEnabled = true
                    layoutShoolPhoneNumber.error = "No Telpon harus di isi"
                    layoutShoolPhoneNumber.errorIconDrawable = null

                    etSchoolPhoneNumber.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutShoolPhoneNumber.isErrorEnabled = false
                    }
                }

                if (etSchoolEmail.text.isNullOrEmpty()){
                    layoutSchoolEmail.isErrorEnabled = true
                    layoutSchoolEmail.error = "Email Sekolah harus di isi"
                    layoutSchoolEmail.errorIconDrawable = null
                }

                if (etTotalStudent.text.isNullOrEmpty()){
                    layoutSchoolTotalStudent.isErrorEnabled = true
                    layoutSchoolTotalStudent.error = "Total murid harus di isi"
                    layoutSchoolTotalStudent.errorIconDrawable = null

                    etTotalStudent.doOnTextChanged { text, start, before, count ->
                        if ((text?.length ?: 0) > 0) layoutSchoolTotalStudent.isErrorEnabled = false
                    }
                }


                if (
                    layoutTypeSchool.isErrorEnabled == false
                    && layoutSchoolName.isErrorEnabled == false
                    && layoutSchoolAddress.isErrorEnabled == false
                    && layoutProvinces.isErrorEnabled == false
                    && layoutSchoolZipCode.isErrorEnabled == false
                    && layoutEtCityDistrict.isErrorEnabled == false
                    && layoutShoolPhoneNumber.isErrorEnabled == false
                    && layoutSchoolEmail.isErrorEnabled == false
                    && layoutSchoolTotalStudent.isErrorEnabled == false
                ){

                    if(dataSchool == null){
                        saveSchoolData()
                    } else {
                        updateData()
                    }

                }
            }

        }
    }

    private fun observeDataAndMoveLayout() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.saveSchoolSuccess.collect {
                if (it == true) {
                    Toast.makeText(requireContext(), "Data Berhasil Tersimpan", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.updateSchoolSuccess.collect {
                if (it == true){
                    Toast.makeText(requireContext(), "Data Berhasil Di Update", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun updateData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            binding.apply {
                val schoolEntity = SchoolEntity(
                    dataId = dataSchool?.dataId ?: 0,
                    image = dataSchool?.image ?: myFile?.readBytes(),
                    schoolType = etTypeSchool.text.toString(),
                    schoolName = etSchoolName.text.toString(),
                    schoolAddress = etSchoolAddress.text.toString(),
                    schoolProvince = etProvinces.text.toString(),
                    schoolZipCode = etSchoolZipCode.text.toString(),
                    schoolCityDistrict = etCityDistrict.text.toString(),
                    schoolNoHp = etSchoolPhoneNumber.text.toString(),
                    schoolEmail = etSchoolEmail.text.toString(),
                    schoolFacebook = etSchoolFacebook.text.toString(),
                    schoolTotalStudent = etTotalStudent.text.toString()
                )
                viewModel.updateSchoolData(schoolEntity)
            }
        }
    }

    private fun saveSchoolData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            binding.apply {
                val schoolEntity = SchoolEntity(
                    image = myFile?.readBytes(),
                    schoolType = etTypeSchool.text.toString(),
                    schoolName = etSchoolName.text.toString(),
                    schoolAddress = etSchoolAddress.text.toString(),
                    schoolProvince = etProvinces.text.toString(),
                    schoolZipCode = etSchoolZipCode.text.toString(),
                    schoolCityDistrict = etCityDistrict.text.toString(),
                    schoolNoHp = etSchoolPhoneNumber.text.toString(),
                    schoolEmail = etSchoolEmail.text.toString(),
                    schoolFacebook = etSchoolFacebook.text.toString(),
                    schoolTotalStudent = etTotalStudent.text.toString()
                )
                viewModel.saveSchoolData(schoolEntity)
            }
        }
    }


    private fun photoResult() {
//        if (arguments != null){
//
//            binding.imgDefault.visibility = View.GONE
//            binding.imgResult.visibility = View.VISIBLE

//            val file = arguments?.getSerializable("picture") as File
//            myFile = file
//            val result = BitmapFactory.decodeFile(file.path)

//        }

        getBackStackData<File>("picture"){
            binding.imgDefault.visibility = View.GONE
            binding.imgResult.visibility = View.VISIBLE

            myFile = it

            val result = BitmapFactory.decodeFile(it.path)
            binding.imgResult.setImageBitmap(result)
        }

    }

    private fun moveToCameraLayout() {
        binding.btnCamera.setOnClickListener {
            findNavController().navigate(R.id.action_formFragment_to_cameraFragment)
        }
    }

    private fun dropdownSchoolType() {
        binding.apply {
            etTypeSchool.setOnClickListener {
                val strings = arrayOf("Negeri", "Suwasta")
                showSchoolTypeDialog(strings, "Tipe Sekolah", etTypeSchool)
            }
        }

    }

    private fun observeCityOrDistrictIndonesia() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.listCityOrDistrict.collect {
                if (!it?.kota_kabupaten.isNullOrEmpty()){
                    val cityOrDistricts = it?.kota_kabupaten
                    binding.apply {
                        etCityDistrict.setOnClickListener {
                            showCityDistrict(cityOrDistricts, "Kota / Kecamanatan", etCityDistrict)
                        }
                    }
                }
            }
        }
    }

    private fun observeProvincesIndonesia() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.listProvince.collect{
                if (!it?.provinsi.isNullOrEmpty()){
                    val provinces = it?.provinsi
                    binding.apply {
                        etProvinces.setOnClickListener {
                            showProvincesDialog(provinces, "Provinsi", etProvinces)
                        }
                    }

                }
            }
        }

    }

    private fun showSchoolTypeDialog(list: Array<String>, title: String, etText: EditText) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setSingleChoiceItems(list, -1){ dialogInterface, i ->
            etText.setText(list[i])
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun showProvincesDialog(list: List<ProvinsiItem>?, title: String, etText: EditText) {
        val provincesName = list?.map { it.nama ?: "" }?.toTypedArray()

        val builder =AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setSingleChoiceItems(provincesName, -1){ dialogInterface, i ->

            list?.find { it.nama == provincesName?.get(i) }?.id?.let {
                viewModel.getCityOrDistricts(it)
            }

            etText.setText(provincesName?.get(i) ?: "")
            dialogInterface.dismiss()
        }
        builder.show()
    }

    private fun showCityDistrict(list: List<CityOrDistrictsItem>?, title: String, etText: EditText) {
        val cityDistricts = list?.map { it.nama ?: "" }?.toTypedArray()

        val builder =AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setSingleChoiceItems(cityDistricts, -1){ dialogInterface, i ->
            etText.setText(cityDistricts?.get(i) ?: "")
            dialogInterface.dismiss()
        }
        builder.show()
    }

}