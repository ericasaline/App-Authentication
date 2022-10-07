package com.app.authentication.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.authentication.database.entity.UserEntity
import com.app.authentication.database.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository): ViewModel() {

    private val _authenticationtStatus = MutableLiveData<Boolean>()
    val authenticationtStatus: LiveData<Boolean> get() = _authenticationtStatus

    private val _userData = MutableLiveData<UserEntity?>()
    val userData: LiveData<UserEntity?> get() = _userData

    fun authentication(email: String, key: String) {
        if( email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || key.isEmpty()) {
            _authenticationtStatus.postValue(false)
        } else {
            viewModelScope.launch {
                val data = repository.check(email, key)

                if(data?.name.isNullOrEmpty() || data?.key.isNullOrEmpty()) {
                    _authenticationtStatus.postValue(false)
                } else {
                    _authenticationtStatus.postValue(true)
                    _userData.postValue(data)
                }
            }
        }
    }
}