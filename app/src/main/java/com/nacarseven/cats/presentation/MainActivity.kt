package com.nacarseven.cats.presentation

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.nacarseven.cats.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleAction()
    }

    private fun handleAction() {
        viewModel.mainAction.observe(this@MainActivity, Observer {
            when (it) {
                MainAction.GoToBreedDetail -> TODO()
                MainAction.GoToBreedListScreen -> TODO()
            }
        })
    }

    private fun showFragment(fragment: Fragment, isReplace: Boolean = false) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (isReplace) {
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        } else {
            fragmentTransaction.add(R.id.fragmentContainerView, fragment)
        }
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.commitAllowingStateLoss()
    }

}


