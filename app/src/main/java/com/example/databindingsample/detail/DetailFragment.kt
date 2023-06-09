package com.example.databindingsample.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.databindingsample.R
import com.example.databindingsample.common.util.pxToDp
import com.example.databindingsample.common.util.safeValue
import com.example.databindingsample.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = checkNotNull(_binding) { "_binding is not initialized." }

    private val viewModel: DetailViewModel by viewModels()

    private val navController by lazy {
        NavHostFragment.findNavController(this)
    }

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DataBindingUtil.inflate<FragmentDetailBinding>(
        inflater,
        R.layout.fragment_detail,
        container,
        false
    ).apply {
        this.lifecycleOwner = viewLifecycleOwner
        this.vm = viewModel
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.detailReturnAction.setOnClickListener {
            navController.navigateUp()
        }
        requireNotNull(args.image.largeImageURL)
        viewModel.setImageInfo(args.image)
        viewModel.setImageHeight(resources.pxToDp(args.image.imageHeight))

        binding.detailImage.minimumHeight = viewModel.imageHeight.safeValue
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}