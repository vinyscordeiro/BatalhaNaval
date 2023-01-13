package pt.isel.batalha_naval.repositories

import pt.isel.batalha_naval.models.UserData

interface UserRepository {
    fun getUserData(): UserData?
    fun setUserData(usr: UserData?)
}