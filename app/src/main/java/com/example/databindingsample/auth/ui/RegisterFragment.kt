package com.example.databindingsample.auth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.databindingsample.R
import com.example.databindingsample.common.util.clearError
import com.example.databindingsample.common.util.collectWithLifecycle
import com.example.databindingsample.common.util.showSnackbar
import com.example.databindingsample.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding: FragmentRegisterBinding
        get() = checkNotNull(_binding) { "_binding is not initialized." }

    private val viewModel: RegisterViewModel by viewModels()

    private val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentRegisterBinding>(
        inflater,
        R.layout.fragment_register,
        container,
        false
    ).apply {
        this.lifecycleOwner = this@RegisterFragment
        this.vm = viewModel
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerNavigateUp.setOnClickListener {
            navController.navigateUp()
        }

        binding.registerEmailInput.addTextChangedListener {
            binding.registerEmailInputContainer.clearError()
        }
        binding.registerPasswordInput.addTextChangedListener {
            binding.registerPasswordInputContainer.clearError()
        }
        binding.registerAgeInput.addTextChangedListener {
            binding.registerAgeInputContainer.clearError()
        }

        collectWithLifecycle(viewModel.uiState) { state ->
            if (state.isUserLoggedIn) {
                binding.root.showSnackbar(R.string.app_name) {
                    viewModel.setErrorDismissed()
                }
            }
            if (state.errorMessage != null) {
                binding.root.showSnackbar(state.errorMessage) {
                    viewModel.setErrorDismissed()
                }
            }
        }
    }

}