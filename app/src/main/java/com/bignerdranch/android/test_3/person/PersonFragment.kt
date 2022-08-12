package com.bignerdranch.android.test_3.person

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.databinding.PersonItemBinding
import com.bignerdranch.android.test_3.model.person.Person

private const val ARG_URL_PERSON = "ARG_URL_PERSON"

class PersonFragment : Fragment() {

    private lateinit var bindingClass: PersonItemBinding
    private lateinit var personViewModel: PersonViewModel
    private lateinit var person: Person
    private val personsFetch = PersonsFetch()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonItemBinding.inflate(inflater)
        personsFetch.fetchPerson(getUrlPerson())
        bindingClass.apply {
            namePerson.text = person.name
        }

        return bindingClass.root
    }

    fun getUrlPerson(): String = requireArguments().getString(ARG_URL_PERSON)!!

    companion object {


        fun newInstance(urlPerson: String?): PersonFragment {
            val args = Bundle().apply {
                putString(ARG_URL_PERSON, urlPerson)
            }

            val fragment = PersonFragment()
            fragment.arguments = args
            return fragment
        }

    }
}