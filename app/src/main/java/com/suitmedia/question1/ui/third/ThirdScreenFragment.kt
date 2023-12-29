package com.suitmedia.question1.ui.third

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.question1.R
import com.suitmedia.question1.databinding.FragmentThirdScreenBinding
import com.suitmedia.question1.ui.second.SecondScreenFragment
import com.suitmedia.question1.utils.NAME
import com.suitmedia.question1.utils.USERNAME
import com.suitmedia.question1.utils.ViewModelFactory

class ThirdScreenFragment : Fragment() {
    private lateinit var binding: FragmentThirdScreenBinding
    private lateinit var adapter: UserListAdapter
    private val viewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            show()
            title = "Third Screen"
        }

        setupRecyclerView()
        observeViewModel()
        setSwipeRefreshListener()
    }

    private fun setupRecyclerView() {
        binding.rvUsers.layoutManager = LinearLayoutManager(requireActivity())

        adapter = UserListAdapter{ username ->
            val secondFragment = parentFragmentManager.findFragmentByTag(SecondScreenFragment::class.java.simpleName)
            secondFragment?.arguments = Bundle().apply {
                putString(USERNAME, username)
            }
            parentFragmentManager.commit {
                secondFragment?.let {
                    replace(R.id.frame_container,
                        it, SecondScreenFragment::class.java.simpleName)
                }
            }
        }

        binding.rvUsers.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { retry() },
            footer = LoadingStateAdapter { retry() }
        )
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setSwipeRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
        }
    }

    private fun retry() {
        adapter.retry()
    }
}