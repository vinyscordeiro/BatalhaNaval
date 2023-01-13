package pt.isel.batalha_naval.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.isel.batalha_naval.domain.Move
import pt.isel.batalha_naval.domain.Square

@Entity()
data class PlayedGame(
    @PrimaryKey val gameId: String,
    val player_one: String,
    val player_two: String,
    val player_one_tiles: List<List<Square>>,
    val player_two_tiles: List<List<Square>>,
    val moves: List<Move>,
) {

}