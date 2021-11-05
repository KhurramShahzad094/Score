package com.khurram.score.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.khurram.score.R
import com.khurram.score.databinding.FragmentScoreBinding
import com.khurram.score.model.ScoreResponse
import com.khurram.score.network.Status
import com.khurram.score.util.Friend
import com.khurram.score.util.gone
import com.khurram.score.util.visible
import com.khurram.score.viewmodel.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    private var scoreResponseModel: ScoreResponse?=null
    private lateinit var binding: FragmentScoreBinding

    private val viewModel by viewModels<ScoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        clickListener()

    }

    private fun clickListener() {
        binding.pbScore.setOnClickListener {
            scoreResponseModel?.let {
                val directions = ScoreFragmentDirections.actionScoreFragmentToScoreDetailFragment(scoreResponse = it)
                findNavController().navigate(directions)
            }

        }
    }

    private fun init() {
        if (Friend.checkInternet(requireContext())) {
            api_getScore()
        } else {
            Friend.showDialog(
                requireContext(),
                getString(R.string.title_information),
                getString(R.string.message_internet),
                getString(
                    R.string.button_ok
                )
            )
        }
    }

    private fun api_getScore() {
        viewModel.api_getScore().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.pbLoader.gone()
                        resource.data?.let { response ->
                            binding.clParent.visible()
                            scoreResponseModel = Gson().fromJson(response, ScoreResponse::class.java)
                            binding.pbScore.max = scoreResponseModel?.creditReportInfo?.maxScoreValue!!
                            binding.pbScore.progress = scoreResponseModel?.creditReportInfo?.score!!
                            binding.tvScore.text = scoreResponseModel?.creditReportInfo?.score!!.toString()
                            binding.tvBottomLabel.text =
                                "Out of ${scoreResponseModel?.creditReportInfo?.maxScoreValue!!}"
                        }

//                        }
                    }
                    Status.ERROR -> {
                        binding.pbLoader.gone()
                            val errorMessage = Friend.showError(it)
                            Friend.showDialog(
                                context = requireContext(),
                                title = getString(R.string.title_error),
                                message = errorMessage,
                                positiveText = getString(R.string.button_ok)
                            )
                    }
                    Status.LOADING -> {
                        binding.pbLoader.visible()
                    }
                }

            }
        })
    }

}