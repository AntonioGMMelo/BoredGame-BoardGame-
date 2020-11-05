import scala.Console.println
import scala.annotation.tailrec
import scala.util.control.Breaks.break

object player {
  private val BOUNDARY_LEFT: Int = 0; //Where is the center pixel on the left most spaces
  private val BOUNDARY_RIGHT: Int = 1000; //where the center pixel on the right most spaces are
  private val BOUNDARY_TOP: Int = 0; //Where the center of the top most spaces are
  private val BOUNDARY_BOTTOM: Int = 1000 //Where the center of the bottom most spaces are
  private val dice = List(1, 2, 3, 4, 5,6) // a six faced dice
  private val wheelItems = List("Move Back 1 Space","Move Back 1 Space","Move Back 1 Space","Move Back 2 Spaces","Move Back 2 Spaces","All Players Move Back 2 Spaces","Move Forward 1 Space","Move Forward 1 Space","Move Forward 1 Space","Move Forward 2 Spaces","Move Forward 2 Spaces","All Players Move Forwards 2 Spaces","Go To Jail","Move Forward 3 Spaces","Stay","Roll The dice","Roll The Weighted Dice") //Wheel options
  private val cards = List("Roll The dice","Roll The Weighted Dice","Go To Jail","Get Out Of Jail Free Card","50/50","Skip Question","Dilate Time")//card options

  def move(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int),forwards: Boolean): (Int,Int) = { //move if forwards == true call moveForward else calls moveBackward
    if(forwards == true) moveForward(NOfSpaces,PixelsPerSpace,CharacterPosition) else moveBackward(NOfSpaces,PixelsPerSpace,CharacterPosition)

  }
    @tailrec
  def moveForward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int,Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
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
      moveForward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
    }else{
      CharacterPosition //return
    }
  }
  @tailrec
  def moveBackward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int,Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
    var CharPositionAfter: (Int, Int) =(0,0)
    if (NOfSpaces > 0) {
      if (BOUNDARY_BOTTOM == CharacterPosition._2 && BOUNDARY_LEFT < CharacterPosition._1) { //Moves up first
        CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 - PixelsPerSpace)
      } else {
        if (BOUNDARY_TOP < CharacterPosition._2 && BOUNDARY_LEFT == CharacterPosition._1) {//then left
          CharPositionAfter = (CharacterPosition._1- PixelsPerSpace, CharacterPosition._2 )
        } else {
          if (BOUNDARY_TOP == CharacterPosition._2 && BOUNDARY_RIGHT > CharacterPosition._1) { //then down
            CharPositionAfter = (CharacterPosition._1 , CharacterPosition._2 + PixelsPerSpace)
          } else {
            CharPositionAfter = (CharacterPosition._1+ PixelsPerSpace, CharacterPosition._2 ) //then right
          }
        }
      }
      moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
    }else{
      CharacterPosition //return
    }
  }
  def throwDice(dice : List[Int]): Int={//random number between 0-dice.length which equates to one of the faces on a dice
      val r = new scala.util.Random
      dice(r.nextInt(dice.size))
  }
  def coinToss(): Int ={//random number between 0-1 equating to a coin toss 1=Heads, 0=Tails
    val r = new scala.util.Random
    r.nextInt(2)
  }
  def weightedDice() : Int ={  //a 3 faced dice with the values 2,4 or 6
      val aux =dice.filter(_ <4)  //filters dice to List(1,2,3).
      lazy val diceRoll = aux.map(x=>x*2) //maps dice to List(2,4,6)
      throwDice(diceRoll)
  }
  def spinTheWheel(wheelItems:List[String]): String ={ //returns a String wth the action where the wheel landed
    val r = new scala.util.Random
    wheelItems(r.nextInt(wheelItems.size)) //Makes the wheel spin random with different odds for different actions because of the number of times an action is in the list
  }
  def pullCard(cards:List[String]): String ={ //returns a String wth the action where the wheel landed
    val r = new scala.util.Random
    cards(r.nextInt(cards.size)) //Makes the wheel spin random with different odds for different actions because of the number of times an action is in the list
  }
}



object user{

  type Name = String
  type Color = Color
  type Player = (Name, Color)
  var Players: List[Player] = List()

  def CreatePlayer(NewName: String, NewColor: Color): Unit = {
    var iterator: Int = 0
    val NewPlayer: Player = (NewName, NewColor)
    while (iterator < Players.length) {
      if (NewName != Players(iterator)) {
        Players :+ NewPlayer
        break
      }
      else {
        println("Player name already exists. Choose another one!")
      }
      iterator += 1
    }
    println("Player created successfully!")
  }

  def EditPlayerName(PreviousName: String, NewNewName: String): Unit = {
    var iterator: Int = 0
    while (iterator < Players.length) {
      if (PreviousName == Players(iterator)) {
        val NewPlayer: Player = (NewNewName, Players(iterator)._2)
        Players.updated(iterator, NewPlayer)
        println("Player edited successfully!")
      }
      else {
        println("Player name doesn't exist.")
      }
    }
  }
}
