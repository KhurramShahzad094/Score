package com.khurram.score.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.khurram.score.R
import com.khurram.score.databinding.FragmentScoreBinding
import com.khurram.score.databinding.FragmentScoreDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScoreDetailFragment : Fragment() {

    private lateinit var binding: FragmentScoreDetailBinding
    val args by navArgs<ScoreDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScoreDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.scoreResponse?.let {
            binding.tvClientReferenceID.text="Reference ID: ${it.creditReportInfo?.clientRef}"
            binding.tvClientStatus.text="Status: ${it.creditReportInfo?.status}"
        }

    }
}