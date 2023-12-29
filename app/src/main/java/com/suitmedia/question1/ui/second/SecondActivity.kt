package com.suitmedia.question1.ui.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.suitmedia.question1.R
import com.suitmedia.question1.utils.NAME

class SecondActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }
        setContentView(R.layout.activity_second)

        val name = intent.getStringExtra(NAME)

        fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(SecondScreenFragment::class.java.simpleName)

        if (fragment == null) {
            name?.let {
                val secondFragment = SecondScreenFragment()
                secondFragment.arguments = Bundle().apply {
                    putString(NAME, name)
                }
                fragmentManager.commit {
                    add(R.id.frame_container, secondFragment, SecondScreenFragment::class.java.simpleName)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val fragment = fragmentManager.findFragmentById(R.id.frame_container)
                if (fragment is SecondScreenFragment) {
                    finish()
                } else {
                    fragmentManager.popBackStack()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}