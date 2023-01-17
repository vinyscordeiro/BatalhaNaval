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

    override var userInfo: UserInfo?
        get() {
            val savedNick = prefs.getString(userNickKey, null)
            return if (savedNick != null)
                UserInfo(savedNick)
            else
                null
        }

        set(value) {
            if (value == null)
                prefs.edit()
                    .remove(userNickKey)
                    .apply()
            else
                prefs.edit()
                    .putString(userNickKey, value.nick)
                    .apply()
        }
}