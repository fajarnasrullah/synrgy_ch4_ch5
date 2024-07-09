package com.jer.ch4_ch5.data.datasource.remote

import com.jer.ch4_ch5.data.datasource.LoginRemoteSource
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.LoginBody
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.LoginResponse
import com.jer.ch4_ch5.data.datasource.remote.retrofit.login.ReqresService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ImplementLoginRemoteTest {

    private val reqresService = mock<ReqresService>()

    private val loginRemoteSource = ImplementLoginRemote(reqresService)

    @Test
    fun hardCodeTest() = runTest{

        //given
        val username = "fajar"
        val password = "12345678"

        //when
        val expected = "qwertyuiopasdfghjklzxcvbnm"
        val actual =loginRemoteSource.login(username, password)

        //then
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun login() = runTest{
        //Given

        val password = "password"
        val username = "username"
        val loginBody = LoginBody(username, password, username)
        val loginResponse = LoginResponse("id", "token")


        //When
        whenever(reqresService.login(loginBody)).thenReturn(loginResponse)
        val expected = "token"
        val actual = loginRemoteSource.login(username, password)

        //Then
        Assert.assertEquals(expected,actual)

    }

    @Test
    fun register() {
    }
}