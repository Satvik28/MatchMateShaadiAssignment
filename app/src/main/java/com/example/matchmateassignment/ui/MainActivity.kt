package com.example.matchmateassignment.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.rootLayout)
        val adapter = UserListAdapter(
            onAcceptClick = {
                userViewModel.changeUserStatus(it.uuid, UserStatus.ACCEPTED)
            },
            onDeclineClick = {
                userViewModel.changeUserStatus(it.uuid, UserStatus.DECLINED)
            }
        )

        with(binding) {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userViewModel.userList.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}