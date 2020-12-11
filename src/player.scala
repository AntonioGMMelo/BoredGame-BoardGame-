import scala.annotation.tailrec

object player {
  private val BOUNDARY_LEFT: Int = 50; //Where is the center pixel on the left most spaces
  private val BOUNDARY_RIGHT: Int = 1050; //where the center pixel on the right most spaces are
  private val BOUNDARY_TOP: Int = 50; //Where the center of the top most spaces are
  private val BOUNDARY_BOTTOM: Int = 1050 //Where the center of the bottom most spaces are
  val dice = List(1, 2, 3, 4, 5, 6) // a six faced dice
  val wheelItems = List("Price Aint Right Round", "Move Back 1 Space", "Move Back 1 Space", "Move Back 1 Space", "Move Back 2 Spaces", "Move Back 2 Spaces", "All Players Move Back 2 Spaces", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 2 Spaces", "Move Forward 2 Spaces", "All Players Move Forwards 2 Spaces", "Go To Jail", "Move Forward 3 Spaces", "Stay", "Roll The dice", "Roll The Weighted Dice") //Wheel options
  val cards = List("Roll The dice", "Roll The Weighted Dice", "Go To Jail", "50/50", "Skip Question", "Dilate Time", "Feud Round", "Feud Round", "Feud Round", "Feud Round", "Feud Round") //card options
  type feud = (String, List[String]) //feud type
  type item = (String, Double) //priceaintright type

  def getSomething[A](list: List[A]): A = { //gets a random item of a list, use "coin" list for a coin toss use "dice" list for a dice roll or "wheelItems" for a wheel spin or "cards" to draw a card or "feudList" for a feud or "itemList" for an item
    val r = new scala.util.Random
    list(r.nextInt(list.size))
  }

  def answerFeud(f: feud, answer: String): Int = { //takes user's answer and compares it to the answers in the feud and returns the number of spaces the user advances according to the answer.
    val aux1 = f._2(0)
    val aux2 = f._2(1)
    val aux3 = f._2(2)
    val aux4 = f._2(3)
    val aux5 = f._2(4)
    answer match {
      case answer if answer.compareTo(aux1) == 0 => 3 //best answer means player moves 3 spaces
      case answer if answer.compareTo(aux2) == 0 => 2 //medium answer means player moves 2 spaces
      case answer if answer.compareTo(aux3) == 0 => 2
      case answer if answer.compareTo(aux4) == 0 => 1 //acceptable answer mean player moves 1 space
      case answer if answer.compareTo(aux5) == 0 => 1
      case _ => 0 //wrong answer no movement for the player
    }
  }

  def priceAintRight(item: item, list: List[Double]): Int = { //returns the index of the player who was closest
    list match {
      case Nil => -1
      case list => list.indexOf(list.minBy(v => math.abs(v - item._2))) //functions that gives the number on the list that is closest to the items price(Inside the indexOF() function)
    }
  }

  def move(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int), f: (Int, Int, (Int, Int)) => (Int, Int)): (Int, Int) = { //higher order functions that calls the function given to it with the atributes given to it i.e. move(3,20,(1000,1000),moveForward) calls moveForward(3, 20, (1000,1000))
    f(NOfSpaces, PixelsPerSpace, CharacterPosition)
  }

  @tailrec
  def moveForward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int, Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
    if (NOfSpaces > 0) {
      if (BOUNDARY_BOTTOM == CharacterPosition._2 && BOUNDARY_LEFT < CharacterPosition._1) { //Moves to the left first
        val CharPositionAfter = (CharacterPosition._1 - PixelsPerSpace, CharacterPosition._2)
        moveForward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
      } else {
        if (BOUNDARY_TOP < CharacterPosition._2 && BOUNDARY_LEFT == CharacterPosition._1) { //then up
          val CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 - PixelsPerSpace)
          moveForward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
        } else {
          if (BOUNDARY_TOP == CharacterPosition._2 && BOUNDARY_RIGHT > CharacterPosition._1) { //then to the right
            val CharPositionAfter = (CharacterPosition._1 + PixelsPerSpace, CharacterPosition._2)
            moveForward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
          } else {
            val CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 + PixelsPerSpace) //then down
            moveForward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
          }
        }
      }
    } else {
      CharacterPosition //return
    }
  }

  @tailrec
  def moveBackward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int, Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
    if (NOfSpaces > 0) {
      if (BOUNDARY_RIGHT == CharacterPosition._1 && BOUNDARY_TOP < CharacterPosition._2) { //Moves up first
        val CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 - PixelsPerSpace)
        moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
      } else {
        if (BOUNDARY_TOP == CharacterPosition._2 && BOUNDARY_LEFT < CharacterPosition._1) { //then left
          val CharPositionAfter = (CharacterPosition._1 - PixelsPerSpace, CharacterPosition._2)
          moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
        } else {
          if (BOUNDARY_LEFT == CharacterPosition._1 && BOUNDARY_BOTTOM < CharacterPosition._2) { //then down
            val CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 + PixelsPerSpace)
            moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
          } else {
            val CharPositionAfter = (CharacterPosition._1 + PixelsPerSpace, CharacterPosition._2) //then right
            moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
          }
        }
      }

    } else {
      CharacterPosition //return
    }
  }

  def weightedDice(): Int = { //a 3 faced dice with the values 2,4 or 6
    val aux = dice.filter(_ < 4) //filters dice to List(1,2,3).
    lazy val aux2 = aux.foldLeft(0)(_ + _) //folds aux list to int 6
    val aux3 = dice.filter(_ < 3) //filters dice to List(1,2).
    val aux4 = aux3.map(x => x * 2) //maps dice to List(2,4)
    val diceRoll = aux4 :+ aux2 //concatenates List(2,4) with 6 = List(2,4,6)
    getSomething(diceRoll).toString.toInt //rolls the dice with the weight
  }
}