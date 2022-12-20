package com.example.myformdatasiswaapp.ui.table

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.databinding.SchoolItemBinding

class SchoolAdapter(
    private val list: List<SchoolEntity>
): RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class SchoolViewHolder(private val binding: SchoolItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(school: SchoolEntity) {
            binding.apply {

                if (school.image != null){
                    val bm = BitmapFactory.decodeByteArray(school.image,0, school.image.size ?: 0)
                    imgSchool.setImageBitmap(bm)
                }

                tvSchoolName.text = school.schoolName
                tvSchoolType.text = school.schoolType
                tvProvince.text = school.schoolProvince

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(school)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = SchoolItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val school = list[position]
        holder.bind(school)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback {
        fun onItemClicked(school: SchoolEntity)
    }
}