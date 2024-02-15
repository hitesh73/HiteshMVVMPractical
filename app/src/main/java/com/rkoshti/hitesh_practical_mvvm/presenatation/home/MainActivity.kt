package com.rkoshti.hitesh_practical_mvvm.presenatation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.databinding.ActivityMainBinding
import com.rkoshti.hitesh_practical_mvvm.presenatation.home_viewmodel.HomeViewModel
import com.rkoshti.hitesh_practical_mvvm.utils.CustomProgressDialog
import com.rkoshti.hitesh_practical_mvvm.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: UserDataAdapter
    private val viewModel: HomeViewModel by viewModels()
    private val myList = mutableListOf<UserResponseItem>()
    private val lists: MutableList<UserResponseItem> = mutableListOf()
    val progressDialog by lazy { CustomProgressDialog(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeHomeData()

        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)

        mAdapter = UserDataAdapter()
        binding.recyclerViewList.adapter = mAdapter

    }

    private fun observeHomeData() {
        viewModel.fetchList.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { resources -> resources?.let { handleFetchData(resources) } }
            .launchIn(lifecycleScope)

//        lifecycleScope.launch {
//            viewModel.fetchList.collect { resources ->
//                resources?.let { handleFetchData(resources) }
//            }
//        }
    }

    private fun handleFetchData(state: Resource<List<UserResponseItem>>) {
        when (state) {
            is Resource.Loading -> handleLoading()
            is Resource.Success -> state.data?.let { list ->
                handleSuccess(list)
            }

            is Resource.Error -> handleError(state.message)
        }
    }

    private fun handleLoading() {
        progressDialog.start("Loading")
    }

    private fun handleSuccess(data: List<UserResponseItem>) {
        progressDialog.stop()
        if (data.isNotEmpty()) {
            binding.txtNoDataFound.visibility = View.GONE
            binding.recyclerViewList.visibility = View.VISIBLE
            Log.e("TAG", "handleSuccess: ${data}")
            setupAdapter(data)
        } else {
            binding.recyclerViewList.visibility = View.GONE
            binding.txtNoDataFound.visibility = View.VISIBLE
        }
    }

    private fun setupAdapter(list: List<UserResponseItem>) {
        mAdapter.updateData(list)
    }

    private fun handleError(error: String?) {
        error?.let {
            progressDialog.stop()
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }
}