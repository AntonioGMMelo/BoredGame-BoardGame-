import scala.annotation.tailrec
import scala.reflect.runtime.universe._

object a {
  val BOUNDARY_LEFT: Int = 0; //Where is the center pixel on the left most spaces
  val BOUNDARY_RIGHT: Int = 1080; //where the center pixel on the right most spaces are
  val BOUNDARY_TOP: Int = 0; //Where the center of the top most spaces are
  val BOUNDARY_BOTTOM: Int = 1080 //Where the center of the bottom most spaces are
    @tailrec
  def Move(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): Unit = {
    var CharPositionAfter: (Int, Int) =(0,0)
    if (NOfSpaces > 0) {
      if (BOUNDARY_BOTTOM == CharacterPosition._2 && BOUNDARY_LEFT < CharacterPosition._1) {
        CharPositionAfter = (CharacterPosition._1 - PixelsPerSpace, CharacterPosition._2)
      } else {
        if (BOUNDARY_TOP < CharacterPosition._2 && BOUNDARY_LEFT == CharacterPosition._1) {
          CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 - PixelsPerSpace)
        } else {
          if (BOUNDARY_TOP == CharacterPosition._2 && BOUNDARY_RIGHT > CharacterPosition._1) {
            CharPositionAfter = (CharacterPosition._1 + PixelsPerSpace, CharacterPosition._2)
          } else {
            CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 + PixelsPerSpace)
          }
        }
      }
      println(CharacterPosition)
      Move(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter)
    }
  }


  def main(args: Array[String]): Unit = {
    Move(3, 20, (1080, 1080))
    //Move(3, 20, (0, 1080))
    //Move(3, 20, (0,0))
    //Move(3, 20, (1080, 0))
  }
}

object Player{
  def CreatePlayer(Name: String, Colour: )
}