package com.bignerdranch.android.test_3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.test_3.Persons.PersonsFragment
import com.bignerdranch.android.test_3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val isfFragmentContainerEmpty = savedInstanceState == null
        if (isfFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, PersonsFragment.newInstance())
                .commit()
        }
    }
}