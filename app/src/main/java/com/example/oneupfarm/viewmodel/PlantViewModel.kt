package com.example.oneupfarm.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oneupfarm.data.api.PlantApi
import com.example.oneupfarm.data.model.Plant
import com.example.oneupfarm.data.model.UserPlant
import com.example.oneupfarm.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlantViewModel(private val plantApi: PlantApi) : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> get() = _message

    private val _navigationEvent = MutableStateFlow<String?>(null)
    val navigationEvent: StateFlow<String?> get() = _navigationEvent

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant: StateFlow<Plant?> get() = _plant

    private val _plants = MutableStateFlow<List<Plant>>(emptyList())
    val plants: StateFlow<List<Plant>> get() = _plants

    private val _userPlant = MutableStateFlow<UserPlant?>(null)
    val userPlant: StateFlow<UserPlant?> get() = _userPlant

    private val _userPlants = MutableStateFlow<List<UserPlant>>(emptyList())
    val userPlants: StateFlow<List<UserPlant>> get() = _userPlants


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

    fun getAllPlants() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = plantApi.getPlants()

                if (response.code() == 401) {
                    Log.i("UNAUTHORIZED", "User is not authenticated")
                    setNavigation(Screen.Login.route)
                }

                if (response.isSuccessful) {
                    val plants = response.body()
                    _plants.value = plants?.results ?: emptyList()
                } else {
                    Log.e("ERROR PLANT VIEW", "Unexpected error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GET USER INFO", "Error at getUserInfo function:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addUserPlant(userPlant: UserPlant) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = plantApi.addUserPlant(userPlant)
                Log.i("ADD USER PLANT", "Response: ${response.code()}")
                setNavigation(Screen.TrackPlant.route)
            } catch (e: Exception) {
                Log.e("ADD USER PLANT", "Error at addUserPlant function:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getAllUserPlants() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = plantApi.getUserPlants()

                if (response.code() == 401) {
                    Log.i("UNAUTHORIZED", "User is not authenticated")
                    setNavigation(Screen.Login.route)
                }

                if (response.isSuccessful) {
                    val userPlants = response.body()
                    _userPlants.value = userPlants?.data ?: emptyList()
                    Log.i("USER PLANTS", "Response: ${userPlants?.data}")
                } else {
                    Log.e("ERROR PLANT VIEW", "Unexpected error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GET USER PLANTS", "Error at getAllUserPlants function:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getDetailUserPlant(userPlantId: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = plantApi.getUserPlantById(userPlantId)

                if (response.code() == 401) {
                    Log.i("UNAUTHORIZED", "User is not authenticated")
                    setNavigation(Screen.Login.route)
                }
                if (response.isSuccessful) {
                    val userPlant = response.body()
                    _userPlant.value = userPlant?.data
                } else {
                    Log.e("ERROR PLANT VIEW", "Unexpected error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("GET USER PLANTS", "Error at getAllUserPlants function:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}