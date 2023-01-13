package pt.isel.batalha_naval.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class FavoriteGame(
    @PrimaryKey val playerName: String,
    val game: PlayedGame
) {

}