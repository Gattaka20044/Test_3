package com.bignerdranch.android.test_3.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.test_3.databinding.PersonItemBinding
import com.bumptech.glide.Glide

private const val ARG_URL_PERSON = "ARG_URL_PERSON"


class PersonFragment : Fragment() {

    private lateinit var personViewModel: PersonViewModel
    private lateinit var bindingClass: PersonItemBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonItemBinding.inflate(inflater)

        return bindingClass.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPersonsViewModel()

    }

    private fun initPersonsViewModel() {
        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            val person = personViewModel.getItemPerson(getUrlPerson())

            bindingClass.apply {
                namePerson.text = "Name: " + person.name
                statusPerson.text = "Status: " + person.status
                speciesPerson.text = "Species: " + person.species
                genderPerson.text = "Gender: " + person.gender
                lastLocation.text = "Location: " + person.location.name
                numberOfEpisodes.text = "Episodes: " + person.episode.size

                Glide.with(imageView)
                    .load(person.image)
                    .into(imageView)

            }
        }
    }

    private fun getUrlPerson(): Int = requireArguments().getInt(ARG_URL_PERSON)!!

    companion object {
        fun newInstance(urlPerson: Int): PersonFragment {
            val args = Bundle().apply {
                putInt(ARG_URL_PERSON, urlPerson)
            }

            val fragment = PersonFragment()
            fragment.arguments = args
            return fragment

        }
    }
}