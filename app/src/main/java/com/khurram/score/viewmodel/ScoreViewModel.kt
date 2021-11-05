package com.khurram.score.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.khurram.score.network.Resource
import com.khurram.score.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor(var networkRepository: NetworkRepository) : ViewModel() {

    fun api_getScore() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = networkRepository.getScore()))
        } catch (ex: Exception) {
            emit(
                Resource.error(
                    data = null, message = ex.message
                        ?: "Error Occurred!", exception = ex
                )
            )
        }
    }

}