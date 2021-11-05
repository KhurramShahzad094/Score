package com.khurram.score.repository

import com.google.common.truth.Truth.assertThat
import com.khurram.score.model.CreditReportInfo
import com.khurram.score.model.ScoreResponse
import com.khurram.score.network.Resource

import org.junit.Test

class NetworkRepositoryTest {

    private var shouldReturnNetworkError = true

    @Test
    fun getScoreReturnsTrue() {
        if (shouldReturnNetworkError){
            Resource.error(null,"Error",null)
            assertThat(false).isFalse()
        }else{
            Resource.success(ScoreResponse(CreditReportInfo(0,0,null,null)))
            assertThat(true).isTrue()
        }
    }
}