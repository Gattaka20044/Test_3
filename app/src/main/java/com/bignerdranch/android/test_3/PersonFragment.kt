package com.bignerdranch.android.test_3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test_3.databinding.PersonItemListBinding
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding
import com.bignerdranch.android.test_3.model.Result

class PersonFragment : Fragment() {

    private lateinit var bindingClass: PersonRecyclerBinding
    private lateinit var personRecyclerView: RecyclerView
    private lateinit var personsViewModel: PersonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personsViewModel = ViewModelProviders.of(this).get(PersonsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        personsViewModel.personsItemViewModel.observe(viewLifecycleOwner, Observer {
            personRecyclerView.adapter = PersonAdapter(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonRecyclerBinding.inflate(inflater)
        personRecyclerView = bindingClass.recyclerPerson
        personRecyclerView.layoutManager = LinearLayoutManager(context)

        return bindingClass.root
    }

    private inner class PersonHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = PersonItemListBinding.bind(item)

        private lateinit var personItem: Result



        fun bind(person: Result) = with(binding) {
            //namePerson.text = "Name:" + person.name
            namePerson.text = getString(R.string.name, person.name)
            statusPerson.text = getString(R.string.status, person.status)
            speciesPerson.text = getString(R.string.species, person.species)
            genderPerson.text = getString(R.string.gender, person.gender)
        }
    }

    private inner class PersonAdapter(private val personItems: List<Result>) : RecyclerView.Adapter<PersonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item_list, parent, false)

            return PersonHolder(view)
        }

        override fun onBindViewHolder(holder: PersonHolder, position: Int) {
            holder.bind(personItems[position])

        }

        override fun getItemCount(): Int = personItems.size

    }

    companion object {
        fun newInstance() = PersonFragment()
    }
}