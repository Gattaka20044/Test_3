package com.bignerdranch.android.test_3.Persons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test_3.R
import com.bignerdranch.android.test_3.databinding.PersonItemListBinding
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding
import com.bignerdranch.android.test_3.model.Persons.Result
import com.bignerdranch.android.test_3.person.PersonFragment
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.collectLatest


class PersonsFragment : Fragment() {

    private lateinit var bindingClass: PersonRecyclerBinding
    private lateinit var recyclerView: RecyclerView
    private var personAdapter = PersonAdapter()
    private lateinit var personsViewModel: PersonsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonRecyclerBinding.inflate(inflater)
        recyclerView = bindingClass.recyclerPerson
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = personAdapter
        return bindingClass.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPersonsViewModel()

    }

    inner class PersonAdapter() :
        PagingDataAdapter<Result, PersonAdapter.PersonsHolder>(DiffUtilCallBack()) {

        inner class PersonsHolder(view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            val binding = PersonItemListBinding.bind(view)
            lateinit var personItem: Result


            fun bind(data: Result) {
                binding.apply {
                    namePerson.text = "Name: " + data.status
                    statusPerson.text = "Status: " + data.status
                    speciesPerson.text = "Species: " + data.species
                    genderPerson.text = "Gender: " + data.gender

                    Glide.with(imageView)
                        .load(data.image)
                        .into(imageView)
                }
            }

            fun bindPerson(item: Result) {
                personItem = item
            }

            override fun onClick(v: View?) {
                val fragment = PersonFragment.newInstance(urlPerson = personItem.id)

                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragmentContainer, fragment)
                    .commit()

            }

            init {
                view.setOnClickListener(this)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsHolder {

            val view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.person_item_list, parent, false)
            return PersonsHolder(view)
        }

        override fun onBindViewHolder(holder: PersonsHolder, position: Int) {
            holder.bind(getItem(position)!!)
            holder.bindPerson(getItem(position)!!)
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name && oldItem.species == newItem.species
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

    }

    private fun initPersonsViewModel() {
        personsViewModel = ViewModelProvider(this).get(PersonsViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {

            personsViewModel.getListData().collectLatest {

                personAdapter.submitData(it)
            }
        }
    }

    companion object {
        fun newInstance() = PersonsFragment()
    }
}