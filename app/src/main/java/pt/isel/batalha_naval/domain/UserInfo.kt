package pt.isel.batalha_naval.domain

/**
 * Represents the user information.
 *
 * @property [nick] the user's nick name
 */
data class UserInfo(val nick: String) {
    init {
        require(validateUserInfoParts(nick))
    }
}

/**
 * Returns a [UserInfo] instance with the received values or null, if those
 * values are invalid.
 */
fun userInfoOrNull(nick: String): UserInfo? =
    if (validateUserInfoParts(nick))
        UserInfo(nick)
    else
        null

/**
 * Checks whether the received values are acceptable as [UserInfo]
 * instance fields.
 */
fun validateUserInfoParts(nick: String) =
    nick.isNotBlank() ?: true
