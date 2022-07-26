package com.bignerdranch.android.test_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                .add(R.id.fragmentContainer, PersonFragment.newInstance())
                .commit()
        }
    }
}