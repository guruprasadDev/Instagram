package com.guruthedev.instagram.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.guruthedev.instagram.IgApplication
import com.guruthedev.instagram.data.SignUpError
import com.guruthedev.instagram.data.pref.SessionPrefHelper
import com.guruthedev.instagram.utils.SignUpErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel() : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val _taskResponseLiveData = MutableLiveData<Task<AuthResult>>()
    val taskResponseLiveData: LiveData<Task<AuthResult>>
        get() = _taskResponseLiveData
    private val _errorLiveData = MutableLiveData<SignUpError>()
    val errorLiveData: LiveData<SignUpError>
        get() = _errorLiveData

    private fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { taskResult ->
                    if (taskResult.isSuccessful) {
                        _taskResponseLiveData.postValue(taskResult)
                    } else {
                        _errorLiveData.postValue(
                            SignUpError(
                                SignUpErrorType.ERROR_API,
                                taskResult.exception?.message
                            )
                        )
                    }
                }
        }
    }

    fun validateCred(fullName: String, username: String, email: String, password: String) {
        if (fullName.isEmpty()) {
            _errorLiveData.value = SignUpError(SignUpErrorType.ERROR_EMPTY_FULL_NAME)
        } else if (username.isEmpty()) {
            _errorLiveData.value = SignUpError(SignUpErrorType.ERROR_EMPTY_USERNAME)
        } else if (email.isEmpty()) {
            _errorLiveData.value = SignUpError(SignUpErrorType.ERROR_EMPTY_EMAIL)
        } else if (password.isEmpty()) {
            _errorLiveData.value = SignUpError(SignUpErrorType.ERROR_EMPTY_PASSWORD)
        } else {
            signUp(email, password)
        }
    }
}
