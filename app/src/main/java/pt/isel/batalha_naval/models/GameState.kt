package pt.isel.batalha_naval.models

enum class GameState(val value : Int = 0)
{
    INVALID(0),
    POSITION(1),
    ONGOING(2),
    ENDED(3)
}