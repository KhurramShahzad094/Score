package com.khurram.score.util

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before

import org.junit.Test

class FriendTest {

    @Before
    fun setup() {

    }

    @After
    fun teardown() {

    }

    @Test
    fun checkInternetReturnsTrue() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()

        val result = Friend.checkInternet(appContext)
        assertThat(result).isTrue()
    }

    @Test
    fun checkInternetReturnsFalse() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()

        val result = Friend.checkInternet(appContext)
        assertThat(result).isFalse()
    }


}