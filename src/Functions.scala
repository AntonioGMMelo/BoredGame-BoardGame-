import java.awt.Color

import scala.{:+, ::}
import scala.annotation.tailrec

object a {
  val BOUNDARY_LEFT: Int = 0; //Where is the center pixel on the left most spaces
  val BOUNDARY_RIGHT: Int = 1080; //where the center pixel on the right most spaces are
  val BOUNDARY_TOP: Int = 0; //Where the center of the top most spaces are
  val BOUNDARY_BOTTOM: Int = 1080 //Where the center of the bottom most spaces are
    @tailrec
  def Move(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): Unit = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
    var CharPositionAfter: (Int, Int) =(0,0)
    if (NOfSpaces > 0) {
      if (BOUNDARY_BOTTOM == CharacterPosition._2 && BOUNDARY_LEFT < CharacterPosition._1) { //Moves to the left first
        CharPositionAfter = (CharacterPosition._1 - PixelsPerSpace, CharacterPosition._2)
      } else {
        if (BOUNDARY_TOP < CharacterPosition._2 && BOUNDARY_LEFT == CharacterPosition._1) {//then up
          CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 - PixelsPerSpace)
        } else {
          if (BOUNDARY_TOP == CharacterPosition._2 && BOUNDARY_RIGHT > CharacterPosition._1) { //then to the right
            CharPositionAfter = (CharacterPosition._1 + PixelsPerSpace, CharacterPosition._2)
          } else {
            CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 + PixelsPerSpace) //then down
          }
        }
      }
      println(CharacterPosition)
      Move(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
    }
  }
}

object b{
  type Name = String
  type Color = Color
  type Player = (Name, Color)
  var Players = List[Player] //único problema: listas são imutáveis. talvez utilizar linked lists? Podemos "criar" uma lista nova toda vez, não é problema

  def CreatePlayer(NewName: String, NewColor: Color): Unit ={
    var NewPlayer: Player = (NewName, NewColor)
    Players :: NewPlayer
    println("Player created successfully!")
  }

  def EditPlayerName(NewName: String): Unit ={

    println("Player edited successfully!")
  }
}