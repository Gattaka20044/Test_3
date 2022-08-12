package com.bignerdranch.android.test_3.person

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.databinding.PersonItemBinding
import com.bignerdranch.android.test_3.model.person.Origin
import com.bignerdranch.android.test_3.model.person.Person
import com.bignerdranch.android.test_3.persons.PersonsViewModel

private const val ARG_URL_PERSON = "ARG_URL_PERSON"

class PersonFragment : Fragment() {

    private lateinit var bindingClass: PersonItemBinding

   // private lateinit var person: LiveData<Person>
    private val personsFetch = PersonsFetch()
    private lateinit var personViewModel: PersonViewModel
    private lateinit var person: Origin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        //personsFetch.fetchPerson(getUrlPerson())

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("onViewCreated", "onViewCreated")
        personViewModel.personItemViewModel.observe(viewLifecycleOwner, Observer {
            person = it
            Log.d("onViewCreated", "{${person.name}}")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonItemBinding.inflate(inflater)
        Log.d("onCreateView", "onCreateView")
        bindingClass.apply {
            namePerson.text = person.name
           // statusPerson.text = person.status
            //speciesPerson.text =
           // genderPerson.text = person.gender
            //lastLocation.text = person.
            //numberOfEpisodes.text = person
        }

        return bindingClass.root
    }


    fun getUrlPerson(): String = requireArguments().getString(ARG_URL_PERSON)!!

    companion object {


        fun newInstance(urlPerson: String): PersonFragment {
            val args = Bundle().apply {
                putString(ARG_URL_PERSON, urlPerson)
            }

            val fragment = PersonFragment()
            fragment.arguments = args
            return fragment
        }

    }
}