package com.suitmedia.question1.ui.second

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.suitmedia.question1.R
import com.suitmedia.question1.databinding.FragmentSecondScreenBinding
import com.suitmedia.question1.ui.third.ThirdScreenFragment
import com.suitmedia.question1.utils.NAME
import com.suitmedia.question1.utils.USERNAME

class SecondScreenFragment : Fragment() {
    private lateinit var binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            show()
            title = "Second Screen"
        }

        val name = arguments?.getString(NAME)
        val username = arguments?.getString(USERNAME)
        Log.i("second", "$username")

        name?.let { binding.tvName.text = it }
        username?.let { binding.tvUsername.text = it }

        binding.btnChoose.setOnClickListener {
            val fragmentManager = parentFragmentManager
            fragmentManager.commit {
                addToBackStack(null)
                replace(R.id.frame_container, ThirdScreenFragment(), ThirdScreenFragment::class.java.simpleName)
            }
        }
    }
}