package com.bignerdranch.android.test_3.Persons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test_3.R
import com.bignerdranch.android.test_3.databinding.PersonItemListBinding
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding
import com.bignerdranch.android.test_3.model.Result

class PersonsRecyclerView() :
    PagingDataAdapter<Result, PersonsRecyclerView.PersonsHolder>(DiffUtilCallBack()) {



    class PersonsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PersonItemListBinding.bind(view)

        fun bind(data: Result) {
            binding.apply {
                namePerson.text = data.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item_list, parent, false)
        return PersonsHolder(view)
    }

    override fun onBindViewHolder(holder: PersonsHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

    }

}