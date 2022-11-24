package com.guruthedev.instagram.viewModel

import com.google.firebase.auth.FirebaseAuth
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before


class SignUpViewModelTest {
    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()
    private val viewModel = SignUpViewModel()
    @MockK
    private lateinit var firebaseAuth:FirebaseAuth
    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun teardown() {
        unmockkAll()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}