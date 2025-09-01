package com.example.matchmateassignment.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchmateassignment.R
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val userListAdapter = UserListAdapter(
            onAcceptClick = {
                userViewModel.changeUserStatus(it.uuid, UserStatus.ACCEPTED)
            },
            onDeclineClick = {
                userViewModel.changeUserStatus(it.uuid, UserStatus.DECLINED)
            }
        )
        val mainCombinedAdapter = userListAdapter.withLoadStateFooter(
            footer = UserLoadStateAdapter(userListAdapter::retry)
        )

        with(binding) {
            recyclerView.adapter = mainCombinedAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            refreshButton.setOnClickListener {
                if (userListAdapter.itemCount == 0) {
                    userListAdapter.refresh()
                } else {
                    userListAdapter.retry()
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    userViewModel.userList.collectLatest {
                        //delay(5000)
                        userListAdapter.submitData(it)
                    }
                }
                launch {
                    userListAdapter.loadStateFlow.collectLatest { loadStates ->

                        when (loadStates.refresh) {
                            is LoadState.Loading -> {
                                binding.progressBar.isVisible = true
                                binding.recyclerView.isVisible = false
                                binding.container.isVisible = false
                            }

                            is LoadState.Error -> {
                                binding.progressBar.isVisible = false
                                if (userListAdapter.itemCount == 0) {
                                    binding.container.isVisible = true
                                } else {
                                    binding.recyclerView.isVisible = true
                                    binding.container.isVisible = false
                                }

                            }

                            is LoadState.NotLoading -> {
                                binding.progressBar.isVisible = false
                                val isEmptyList = userListAdapter.itemCount == 0
                                binding.emptyDefaultView.isVisible = isEmptyList
                                binding.recyclerView.isVisible = !isEmptyList
                                binding.container.isVisible = isEmptyList
                            }
                        }

                        when (loadStates.append) {
                            is LoadState.Error -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Error occurred",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is LoadState.Loading -> {
                                // do nothing
                            }

                            is LoadState.NotLoading -> {
                                // do nothing
                            }
                        }

                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}

