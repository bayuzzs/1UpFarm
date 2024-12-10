package com.example.oneupfarm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.example.oneupfarm.data.model.Gender
import com.example.oneupfarm.data.model.User
import com.example.oneupfarm.data.repository.AuthRepository
import com.example.oneupfarm.ui.navigation.Screen
import com.example.oneupfarm.utils.decodeJwt
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> get() = _message

    private val _navigationEvent = MutableStateFlow<Screen?>(null)
    val navigationEvent: StateFlow<Screen?> get() = _navigationEvent

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> get() = _user

    init {
        fetchToken()
    }

    private fun fetchToken() {
        Log.i("FETCH TOKEN", "FETCH TOKEN CALLED")
        val token = repository.getToken()
        _token.value = token
    }

    fun setMessage(message: String) {
        _message.value = message
    }

    fun clearMessage() {
        _message.value = null
    }

    fun resetNavigate() {
        _navigationEvent.value = null
    }

    fun setUser(name: String, email: String, password: String, gender: Gender) {
        _user.value = User(
            userId = null,
            name = name,
            email = email,
            gender = gender, password = password
        )
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.login(email, password)
                fetchToken()
                setMessage("Berhasil Login")
                _navigationEvent.value = Screen.Profile
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
                _navigationEvent.value = Screen.Login
            } catch (e: Exception) {
                setMessage("Gagal Daftar")
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun getUserInfo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                Log.i("GET USER INFO", "Before Fetch")
                fetchToken()
                Log.i("GET USER INFO", "After Fetch ")
                val tokenValue = _token.value
                if (tokenValue.isNullOrEmpty()) {
                    Log.i("AUTH", "USER NOT AUTHENTICATED")
                    Log.i("AUTH", tokenValue.toString())
                    _user.value = null
                }

                if (!tokenValue.isNullOrEmpty()) {
//                    val payloadMap = decodeJwt(tokenValue)
                    val jwt: JWT = JWT(tokenValue)
                    Log.i("AUTH", "USER AUTHENTICATED")
                    Log.i("PAYLOAD MAP", jwt.toString())
                    _user.value = User(
                        userId = jwt.getClaim("userId")
                            .asInt(),
                        name = jwt.getClaim("name").asString()!!,
                        email = jwt.getClaim("email").asString()!!,
                        gender = Gender.valueOf(jwt.getClaim("gender").asString()!!),
                        password = null
                    )
                    Log.i("GET USERINFO - USER", _user.value.toString())
                }


            } catch (e: Exception) {
                Log.e("GetUserInfo()", "Error at getUserInfo function:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearToken() {
        repository.clearToken()
        fetchToken()
    }
}


