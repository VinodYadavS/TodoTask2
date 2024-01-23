package com.task.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.task.R
import com.task.core.domain.model.UniversityList
import com.task.databinding.UniversityItemBinding

class UniversityListAdapter(
    private val context: Context,
    private val universityList: ArrayList<UniversityList>,
) : RecyclerView.Adapter<UniversityListAdapter.UniversityViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateUniversityList(newuniversityList: ArrayList<UniversityList>) {
        universityList.clear()
        universityList.addAll(newuniversityList)
        notifyDataSetChanged()
    }

    inner class UniversityViewHolder(private val binding: UniversityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: UniversityList,
            context: Context,
            channelList: ArrayList<UniversityList>,
        ) {
            binding.tvname.text = data.name


            binding.executePendingBindings()

            binding.channelItem.rootView.setOnClickListener {
                val position = adapterPosition
                val name = channelList[position].name
                val code = channelList[position].code
                val country = channelList[position].country
                val bundle = Bundle()
                bundle.putString("name", name)
                bundle.putString("code", code)
               bundle.putString("country", country)
                it.findNavController()
                    .navigate(R.id.detailsFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UniversityItemBinding.inflate(inflater, parent, false)
        return UniversityViewHolder(binding)
    }

    override fun getItemCount() = universityList.size

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val data = universityList[position]
        holder.bind(data, context, universityList)
    }


}