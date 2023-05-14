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
import com.example.databindingsample.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = checkNotNull(_binding) { "_binding is not initialized." }

    private val viewModel: LoginViewModel by viewModels()

    private val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentLoginBinding>(
        inflater,
        R.layout.fragment_login,
        container,
        false
    ).apply {
        this.lifecycleOwner = this@LoginFragment
        this.vm = viewModel
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        collectWithLifecycle(viewModel.uiState) { state ->
            if (state.isUserLoggedIn) {
                navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
            if (state.errorMessage != null) {
                binding.root.showSnackbar(state.errorMessage) {
                    viewModel.setErrorDismissed()
                }
            }
        }

        binding.loginNavigatToRegisterPageAction.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        binding.loginEmailInput.addTextChangedListener {
            binding.loginEmailInputContainer.clearError()
        }
        binding.loginPasswordInput.addTextChangedListener {
            binding.loginPasswordInputContainer.clearError()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}