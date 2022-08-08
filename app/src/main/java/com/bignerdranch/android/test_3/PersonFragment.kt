package com.bignerdranch.android.test_3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test_3.databinding.PersonItemListBinding
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding

class PersonFragment : Fragment() {

    private lateinit var bindingClass: PersonRecyclerBinding
    private lateinit var personRecyclerView: RecyclerView

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

        private lateinit var personItem: PersonsItem

        fun bind(person: PersonsItem) = with(binding) {
            namePerson.text = person.name
        }
    }

    private inner class PersonAdapter(private val personItems: List<PersonsItem>) : RecyclerView.Adapter<PersonHolder>() {
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