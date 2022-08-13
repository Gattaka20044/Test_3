package com.bignerdranch.android.test_3.person

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bignerdranch.android.test_3.PersonsFetch
import com.bignerdranch.android.test_3.R
import com.bignerdranch.android.test_3.ThumbnailDownloader
import com.bignerdranch.android.test_3.databinding.PersonItemBinding
import com.bignerdranch.android.test_3.model.person.Origin
import com.bignerdranch.android.test_3.model.person.Person
import com.bignerdranch.android.test_3.persons.PersonsFragment
import com.bignerdranch.android.test_3.persons.PersonsViewModel


private const val ARG_URL_PERSON = "ARG_URL_PERSON"

class PersonFragment : Fragment() {

    private lateinit var bindingClass: PersonItemBinding
    private lateinit var person: Person
    private var personViewModel = PersonViewModel()
    private lateinit var thumbnailDownloader: ThumbnailDownloader<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
        personViewModel.fetchUrlPerson(getUrlPerson())

        val responseHandler = Handler()
        thumbnailDownloader = ThumbnailDownloader(responseHandler) { _, bitmap ->
            val drawable = BitmapDrawable(resources, bitmap)
            bindingClass.imageView.setImageDrawable(drawable)

        }
        lifecycle.addObserver(thumbnailDownloader.fragmentLifecycleObserver)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        personViewModel.personItemViewModel.observe(viewLifecycleOwner, Observer {
            person = it
            bindingClass.apply {
                namePerson.text = getString(R.string.name, person.name)
                statusPerson.text = getString(R.string.status, person.status)
                speciesPerson.text = getString(R.string.name, person.name)
                genderPerson.text = getString(R.string.gender, person.gender)
                lastLocation.text = getString(R.string.last_location, person.location.name)
                numberOfEpisodes.text = getString(R.string.number_of_episodes, person.episode.size.toString())
                thumbnailDownloader.queueThumbnail(view,person.image)
            }
        })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonItemBinding.inflate(inflater)
        //Log.d("onCreateView", "onCreateView")


        return bindingClass.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        viewLifecycleOwner.lifecycle.removeObserver(thumbnailDownloader.viewLifecycleObserver)
    }

    override fun onDestroy() {
        super.onDestroy()

        lifecycle.removeObserver(thumbnailDownloader.viewLifecycleObserver)
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