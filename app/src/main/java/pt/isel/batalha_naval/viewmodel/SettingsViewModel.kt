package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import pt.isel.batalha_naval.domain.UserInfo
import pt.isel.batalha_naval.repositories.UserInfoRepository

class SettingsViewModel(
    private val userRepo: UserInfoRepository
) : ViewModelBase() {

    var username by mutableStateOf("")

    fun userDataExists(): Boolean {
        return userRepo.getUserInfo() != null
    }

    fun loadUserData()
    {
        val userInfo = userRepo.getUserInfo() ?: return
        username = userInfo.nick

    }

    fun saveUserData() {
        userRepo.setUserInfo(UserInfo(username))
    }
}