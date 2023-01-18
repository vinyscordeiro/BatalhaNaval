package pt.isel.batalha_naval.domain


/**
 * Represents the Square in the Battleship board
 */
data class Square(var shot : Boolean, val boat: Boat?){

}