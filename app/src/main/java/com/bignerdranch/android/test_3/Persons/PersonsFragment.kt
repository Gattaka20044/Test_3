package com.bignerdranch.android.test_3.Persons


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.test_3.databinding.PersonRecyclerBinding
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