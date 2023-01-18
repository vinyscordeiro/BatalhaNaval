package pt.isel.batalha_naval.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import pt.isel.batalha_naval.domain.Square
import pt.isel.batalha_naval.models.GameState
import pt.isel.batalha_naval.models.RemoteGame
import pt.isel.batalha_naval.repositories.UserInfoRepository

class GameViewModel(
    private val userInfoRepository: UserInfoRepository
) : ViewModelBase() {

    private lateinit var _remoteGame: RemoteGame

    var gameState: GameState by mutableStateOf(GameState.INVALID)
    var canPlay by mutableStateOf(false)
    var playerWon = false

    private var _plays = 0
    private var _boardFirstPlayer = mutableStateListOf<List<Square>>(emptyList())
    var usernamePlayer by mutableStateOf<String>("")

fun startGame(gameId: String) {
    usernamePlayer = userInfoRepository.getUserInfo()!!.nick
}

private suspend fun waitForPlayerAndNotifyItsMove() {

}


private fun handleGameEnding() {
    if (gameState == GameState.ONGOING) {
        playerWon = true
        gameState = GameState.ENDED
    }
}
    private fun addToFavorite() {

        if (gameState == GameState.ENDED) {
            playerWon = true
            gameState = GameState.ENDED
        }
    }



fun leaveGame() {
    safeViewModelScopeLaunch {
        //
        //  As is this can cause a race condition where the player that ended the game
        //  leaves the game before the other checks its move, the leave as is implemented
        //  will delete the document on firestore thus causing this issue
        //
    }
}

private fun changePlayer() {
    //_currPlayer = if (_currPlayer == CellState.O) CellState.X else CellState.O
}


}
