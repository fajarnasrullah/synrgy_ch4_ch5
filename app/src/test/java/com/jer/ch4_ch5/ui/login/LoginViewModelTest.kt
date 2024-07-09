package com.jer.ch4_ch5.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jer.ch4_ch5.MainDispatcherRule
import com.jer.ch4_ch5.domain.repository.LoginRepository
import com.jer.ch4_ch5.domain.usecase.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val loginUseCase = mock<LoginUseCase>()
    private val viewModel = LoginViewModel(loginUseCase)

    private val worksObserver = mock<Observer<Boolean>>()
    private val errorObserver = mock<Observer<String>>()

    private val worksCaptor = argumentCaptor<Boolean>()
    private val errorCaptor = argumentCaptor<String>()


    @Test
    fun login() = runTest {

        //Given

        val username = "username"
        val password = "password"

        val worksLiveData =viewModel.works
        val errorLiveData = viewModel.error

        worksLiveData.observeForever(worksObserver)
        errorLiveData.observeForever(errorObserver)

        //When
        whenever(loginUseCase.login(username, password)).thenReturn(Unit)
        viewModel.login(username, password)

        //Verify
        verify(worksObserver).onChanged(worksCaptor.capture())
        Assert.assertEquals(worksCaptor.allValues, listOf(true))

    }


    @Test
    fun loginErrorHttp() = runTest {
        // given
        val username = "username"
        val password = "password"


        val worksLiveData = viewModel.works
        val errorLiveData = viewModel.error


        worksLiveData.observeForever(worksObserver)
        errorLiveData.observeForever(errorObserver)

        // when
        val reqresErrorResponseJson = "{\"error\": \"terdapat kesalahan\"}"
        val httpException = HttpException(
            Response.error<ErrorResponse>(
                400,
                ResponseBody.create(
                    "application/json".toMediaTypeOrNull(),
                    reqresErrorResponseJson,
                ),
            ),
        )
        whenever(loginUseCase.login(username, password)).thenThrow(httpException)
        viewModel.login(username, password)

        // verify
        verify(errorObserver).onChanged(errorCaptor.capture())
        Assert.assertEquals(errorCaptor.allValues, listOf("terdapat kesalahan"))
    }

    @Test
    fun loginErrorGeneral() = runTest {
        // given
        val username = "username"
        val password = "password"


        val worksLiveData = viewModel.works
        val errorLiveData = viewModel.error

        worksLiveData.observeForever(worksObserver)
        errorLiveData.observeForever(errorObserver)

        // when
        val throwable = UnsupportedOperationException("error")
        whenever(loginUseCase.login(username, password)).thenThrow(throwable)
        viewModel.login(username, password)

        // verify
        verify(errorObserver).onChanged(errorCaptor.capture())
        Assert.assertEquals(errorCaptor.allValues, listOf("error"))
    }

}