package com.example.databindingsample.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingsample.R
import com.example.databindingsample.common.util.collectWithLifecycle
import com.example.databindingsample.common.util.showSnackbar
import com.example.databindingsample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = checkNotNull(_binding) { "_binding is not initialized." }

    private val viewModel: HomeViewModel by viewModels()

    private val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentHomeBinding>(
        inflater,
        R.layout.fragment_home,
        container,
        false
    ).apply {
        this.lifecycleOwner = viewLifecycleOwner
        this.vm = viewModel
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listAdapter = ImageListAdapter {
            Toast.makeText(context, it.userName, Toast.LENGTH_SHORT).show()
        }
        binding.homeImageList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        collectWithLifecycle(viewModel.uiState) { state ->
            binding.homeProgress.isVisible = state.shouldShowProgress
            if(state.shouldShowImageList){
                binding.homeImageList.isVisible = true
                listAdapter.submitList(state.imageList)
            } else {
                binding.homeImageList.isVisible = false
                listAdapter.submitList(emptyList())
            }

            if (state.shouldShowSnackbar) {
                if (state.errorMessageResId != null) {
                    binding.root.showSnackbar(state.errorMessageResId) {
                        viewModel.setErrorDismissed()
                    }
                } else if (state.errorMessage != null) {
                    binding.root.showSnackbar(state.errorMessage) {
                        viewModel.setErrorDismissed()
                    }
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}