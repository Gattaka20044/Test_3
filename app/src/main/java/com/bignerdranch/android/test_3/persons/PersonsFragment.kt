package com.bignerdranch.android.test_3.persons

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bignerdranch.android.test_3.R
import com.bignerdranch.android.test_3.ThumbnailDownloader
import com.bignerdranch.android.test_3.databinding.PersonItemListBinding
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding
import com.bignerdranch.android.test_3.model.persons.Result
import com.bignerdranch.android.test_3.person.PersonFragment
import com.bignerdranch.android.test_3.persons.PersonsViewModel

private const val ARG_URL_PERSON = "ARG_URL_PERSON"

class PersonsFragment : Fragment() {

    private lateinit var bindingClass: PersonRecyclerBinding
    private lateinit var personRecyclerView: RecyclerView
    private lateinit var personsViewModel: PersonsViewModel
    private lateinit var thumbnailDownloader: ThumbnailDownloader<PersonHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        personsViewModel = ViewModelProviders.of(this).get(PersonsViewModel::class.java)

        val responseHandler = Handler()
        thumbnailDownloader = ThumbnailDownloader(responseHandler) { personHolder, bitmap ->
            val drawable = BitmapDrawable(resources, bitmap)
            personHolder.binding.imageView.setImageDrawable(drawable)
        }
        lifecycle.addObserver(thumbnailDownloader.fragmentLifecycleObserver)
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

    private inner class PersonHolder(item: View) : RecyclerView.ViewHolder(item), View.OnClickListener {
        val binding = PersonItemListBinding.bind(item)

        private lateinit var personItem: Result

        fun bind(person: Result) = with(binding) {
            namePerson.text = getString(R.string.name, person.name)
            statusPerson.text = getString(R.string.status, person.status)
            speciesPerson.text = getString(R.string.species, person.species)
            genderPerson.text = getString(R.string.gender, person.gender)
        }

        fun bindPersonItem(item: Result) {
            personItem = item
        }

        override fun onClick(v: View?) {


            val fragment = PersonFragment.newInstance(urlPerson = personItem.url)

            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainer,fragment)
                .commit()
        }

        init {
            item.setOnClickListener(this)
        }
    }

    private inner class PersonAdapter(private val personItems: List<Result>) : RecyclerView.Adapter<PersonHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item_list, parent, false)

            return PersonHolder(view)
        }

        override fun onBindViewHolder(holder: PersonHolder, position: Int) {
            holder.bind(personItems[position])
            holder.bindPersonItem(personItems[position])
            thumbnailDownloader.queueThumbnail(holder, personItems[position].image)

        }

        override fun getItemCount(): Int = personItems.size

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner.lifecycle.removeObserver(thumbnailDownloader.viewLifecycleObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(thumbnailDownloader.viewLifecycleObserver)
    }



    companion object {

        fun newInstance() = PersonsFragment()
    }
}