package pt.isel.batalha_naval.repositories

import android.content.Context
import pt.isel.batalha_naval.domain.UserInfo

/**
 * A user information repository implementation supported in shared preferences
 */
class UserInfoRepositorySharedPrefs(private val context: Context): UserInfoRepository {

    private val userNickKey = "Nick"

    private val prefs by lazy {
        context.getSharedPreferences("UserInfoPrefs", Context.MODE_PRIVATE)
    }

    override fun getUserInfo(): UserInfo? {
        val savedNick = prefs.getString(userNickKey, null)
        return if (savedNick != null)
            UserInfo(savedNick)
        else
            null
    }

    override fun setUserInfo(userInfo: UserInfo?){
        if (userInfo == null)
            prefs.edit()
                .remove(userNickKey)
                .apply()
        else
            prefs.edit()
                .putString(userNickKey, userInfo.nick)
                .apply()
    }

}