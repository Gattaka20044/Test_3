package com.bignerdranch.android.test_3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.test_3.databinding.PersonItemBinding

private const val ARG_URL_PERSON = "ARG_URL_PERSON"

class PersonFragment : Fragment() {

    private lateinit var bindingClass: PersonItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonItemBinding.inflate(inflater)

        bindingClass.apply {
            namePerson.text = getUrlPerson()
        }

        return bindingClass.root
    }

    private fun getUrlPerson(): String = requireArguments().getString(ARG_URL_PERSON)!!

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