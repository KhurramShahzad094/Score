package com.khurram.score.repository

import com.khurram.score.network.APIsInterface
import javax.inject.Inject

class NetworkRepository @Inject constructor( var apIsInterface: APIsInterface) {

    suspend fun getScore() = apIsInterface.getScore()

}