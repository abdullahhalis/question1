package com.suitmedia.question1.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suitmedia.question1.R
import com.suitmedia.question1.databinding.ActivityThirdBinding
import com.suitmedia.question1.utils.USERNAME
import com.suitmedia.question1.utils.ViewModelFactory

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var adapter: UserListAdapter
    private val viewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
            title = getString(R.string.third_screen)
        }
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        setSwipeRefreshListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
    private fun setupRecyclerView() {
        binding.rvUsers.layoutManager = LinearLayoutManager(this)

        adapter = UserListAdapter{ username ->
            val intent = Intent()
            intent.putExtra(USERNAME, username)
            setResult(200, intent)
            finish()
        }

        binding.rvUsers.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { adapter.retry() },
            footer = LoadingStateAdapter { adapter.retry() }
        )
    }

    private fun observeViewModel() {
        viewModel.users.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }

    private fun setSwipeRefreshListener() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            adapter.notifyDataSetChanged()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}