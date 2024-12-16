package com.example.oneupfarm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.oneupfarm.data.model.Avatar
import com.example.oneupfarm.data.model.Gender
import com.example.oneupfarm.data.model.User
import com.example.oneupfarm.data.repository.AuthRepository
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.utils.decodeJwt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> get() = _message

    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> get() = _navigationEvent

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    init {
        fetchToken()
    }

    private fun fetchToken() {
        Log.i("FETCH TOKEN", "FETCH TOKEN CALLED")
        val token = repository.getToken()
        _token.value = token
        Log.i("FETCH TOKEN", if (_token.value.isNullOrEmpty()) "NULL GUYS" else "NOT NULL")
    }

    fun setMessage(message: String) {
        _message.value = message
    }

    fun clearMessage() {
        _message.value = null
    }

    fun setNavigation(screen: String) {
        _navigationEvent.value = screen
    }

    fun resetNavigate() {
        _navigationEvent.value = null
    }

    fun setUser(name: String, email: String, password: String, gender: Gender) {
        _user.value = User(
            userId = null,
            name = name,
            email = email,
            gender = gender,
            password = password,
        )
        setNavigation(Screen.ChooseGender.route)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.login(email, password)
                fetchToken()
                setMessage("Berhasil Login")
                _navigationEvent.value = Screen.Profile.route
            } catch (e: Exception) {
                Log.e("LOGIN", "Error at login function:", e)
                setMessage("Login Gagal, Coba Lagi")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(name: String, email: String, password: String, gender: Gender) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.register(name, email, password, gender)
                fetchToken()
                setMessage("Berhasil Daftar!")
                delay(500)
                _navigationEvent.value = Screen.Login.route
            } catch (e: Exception) {
                setMessage("Gagal Daftar")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getUserInfo()
//                Log.i("RESPONSE", response.toString())
                if (response.isSuccessful) {
                    val user = response.body()
                    _user.value = user
                    Log.i("USER VALUE", _user.value.toString())
                } else if (response.code() == 401) {
                    Log.i("UNAUTHORIZED", "User is not authenticated")
                    setNavigation(Screen.Login.route)
                    clearToken()
                } else {
                    Log.e("ERROR", "Unexpected error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GET USER INFO", "Error at getUserInfo function:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearToken() {
        _token.value = null
        repository.clearToken()
        fetchToken()
    }
}


