package com.bignerdranch.android.test_3.Persons


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager


import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding
import kotlinx.android.synthetic.main.person_recycler.*
import kotlinx.coroutines.flow.collectLatest

class PersonsFragment : Fragment() {

    private lateinit var bindingClass: PersonRecyclerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var personRecyclerViewAdapter: PersonsRecyclerView
    private lateinit var personsViewModel: PersonsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPersonsViewModel()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingClass = PersonRecyclerBinding.inflate(inflater)
        recyclerView = bindingClass.recyclerPerson

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PersonsRecyclerView()
        return bindingClass.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        personsViewModel.personsItemViewModel.observe(viewLifecycleOwner, Observer {
//            personRecyclerView.adapter = PersonAdapter(it)
//        })
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        bindingClass = PersonRecyclerBinding.inflate(inflater)
//        personRecyclerView = bindingClass.recyclerPerson
//        personRecyclerView.layoutManager = LinearLayoutManager(context)
//
//        return bindingClass.root
//    }





//        fun bind(person: Result) = with(binding) {
//            //namePerson.text = "Name:" + person.name
//            namePerson.text = getString(R.string.name, person.name)
//            statusPerson.text = getString(R.string.status, person.status)
//            speciesPerson.text = getString(R.string.species, person.species)
//            genderPerson.text = getString(R.string.gender, person.gender)
//        }
//    }


    private fun initPersonsViewModel() {
        val personsViewModel = ViewModelProvider(this).get(PersonsViewModel::class.java)
        lifecycleScope.launchWhenCreated {

            personsViewModel.getListData().collectLatest {
                PersonsRecyclerView().submitData(it)
            }
        }
    }



    companion object {
        fun newInstance() = PersonsFragment()
    }
}