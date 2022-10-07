package com.app.authentication.ui.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.authentication.database.entity.UserEntity
import com.app.authentication.database.repository.Repository
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: Repository): ViewModel() {

    private val _insertStatus = MutableLiveData<Boolean>()
    val insertStatus: LiveData<Boolean> get() = _insertStatus

    private val _invalidField = MutableLiveData<Boolean>()
    val invalidField: LiveData<Boolean> get() = _invalidField

    fun getData(name: String, email: String, key: String) {
       val data = UserEntity(name, email, key)

       if(name.isEmpty() || email.isEmpty() || key.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           _invalidField.postValue(true)
       } else {
           viewModelScope.launch {
               _insertStatus.postValue(repository.insert(data))
           }
       }
    }
}