import scala.Console.println
import scala.annotation.tailrec
import scala.util.control.Breaks.break
import io.AnsiColor._
import scala.io.AnsiColor
import scala.io.AnsiColor._

class player {
  private val BOUNDARY_LEFT: Int = 0; //Where is the center pixel on the left most spaces
  private val BOUNDARY_RIGHT: Int = 1000; //where the center pixel on the right most spaces are
  private val BOUNDARY_TOP: Int = 0; //Where the center of the top most spaces are
  private val BOUNDARY_BOTTOM: Int = 1000 //Where the center of the bottom most spaces are
  private val dice = List(1, 2, 3, 4, 5, 6) // a six faced dice
  private val wheelItems = List("Move Back 1 Space", "Move Back 1 Space", "Move Back 1 Space", "Move Back 2 Spaces", "Move Back 2 Spaces", "All Players Move Back 2 Spaces", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 2 Spaces", "Move Forward 2 Spaces", "All Players Move Forwards 2 Spaces", "Go To Jail", "Move Forward 3 Spaces", "Stay", "Roll The dice", "Roll The Weighted Dice") //Wheel options
  private val cards = List("Roll The dice", "Roll The Weighted Dice", "Go To Jail", "Get Out Of Jail Free Card", "50/50", "Skip Question", "Dilate Time") //card options

  def move(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int), f: (Int, Int, (Int, Int)) => (Int, Int)): (Int, Int) = { //higher order functions that calls the function given to it with the atributes given to it i.e. move(3,20,(1000,1000),moveForward) calls moveForward(3, 20, (1000,1000))
    f(NOfSpaces, PixelsPerSpace, CharacterPosition)
  }

  @tailrec
  def moveForward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int, Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
    @tailrec
    private def moveForward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int, Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
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

      private def moveBackward(NOfSpaces: Int, PixelsPerSpace: Int, CharacterPosition: (Int, Int)): (Int, Int) = { //Moves the character by PixelsPerSpace NOfSpaces times movement is like a square starts at the bottom right and
        if (NOfSpaces > 0) {
          if (BOUNDARY_BOTTOM == CharacterPosition._2 && BOUNDARY_LEFT < CharacterPosition._1) { //Moves up first
            val CharPositionAfter = (CharacterPosition._1, CharacterPosition._2 - PixelsPerSpace)
            moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
          } else {
            if (BOUNDARY_TOP < CharacterPosition._2 && BOUNDARY_LEFT == CharacterPosition._1) { //then left
              val CharPositionAfter = (CharacterPosition._1 - PixelsPerSpace, CharacterPosition._2)
              moveBackward(NOfSpaces - 1, PixelsPerSpace, CharPositionAfter) //recursivity
            } else {
              if (BOUNDARY_TOP == CharacterPosition._2 && BOUNDARY_RIGHT > CharacterPosition._1) { //then down
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

      def throwDice(dice: List[Int]): Int = { //random number between 0-dice.length which equates to one of the faces on a dice
        val r = new scala.util.Random
        dice(r.nextInt(dice.size))
      }

      def coinToss(): Int = { //random number between 0-1 equating to a coin toss 1=Heads, 0=Tails
        val r = new scala.util.Random
        r.nextInt(2)
      }

      def weightedDice(): Int = { //a 3 faced dice with the values 2,4 or 6
        val aux = dice.filter(_ < 4) //filters dice to List(1,2,3).
        lazy val diceRoll = aux.map(x => x * 2) //maps dice to List(2,4,6)
        throwDice(diceRoll)
      }

      def spinTheWheel(wheelItems: List[String]): String = { //returns a String wth the action where the wheel landed
        val r = new scala.util.Random
        wheelItems(r.nextInt(wheelItems.size)) //Makes the wheel spin random with different odds for different actions because of the number of times an action is in the list
      }

      def pullCard(cards: List[String]): String = { //returns a String wth the action where the wheel landed
        val r = new scala.util.Random
        cards(r.nextInt(cards.size)) //Makes the wheel spin random with different odds for different actions because of the number of times an action is in the list
      }
    }


    object user {

      type Name = String
      type Color = (String, Boolean)
      type Player = (Name, Color)
      val Colors: List[Color] = List((BLACK, false), (WHITE, false), (BLUE, false), (CYAN, false), (RED, false), (GREEN, false), (MAGENTA, false), (YELLOW, false))
      val Players: List[Player] = List() //Podemos "criar" uma lista nova toda vez, não é problema

      def CreatePlayer(NewName: Name, NewColor: Int): Unit = {
        val NewPlayerColor: Color = Colors(NewColor)
        val NewPlayer: Player = (NewName, NewPlayerColor)

        IsTheNameUsed(Players, NewName) match {
          case true => println("Player name already exists. Choose another name.")
          case false => {
            NewPlayerColor._2 match{
              case false =>
                Players :+ NewPlayer
                Colors.updated(NewColor, (Colors(NewColor)._1, true))
              case true => println("Player color is already selected. Choose another color.")
            }
            println(s"${NewPlayerColor._1}${BOLD}Player created successfully!${RESET}")
          }
        }
      }

      def EditPlayerName(PreviousName: Name, NewNewName: Name): Unit = {
        val EditedPlayer: Player = (NewNewName, Players(Players.indexOf(PreviousName))._2)

        IsTheNameUsed(Players, PreviousName) match {
          case false => println("Player name does not exist. Choose an existent name to be changed.")
          case true =>
            Players.updated(Players.indexOf(PreviousName), EditedPlayer)
            println(s"${Players(Players.indexOf(EditedPlayer))._2._1}${BOLD}Player edited successfully!${RESET}")
        }
      }

      @tailrec
      def IsTheNameUsed(ListOfPlayers: List[Player], NameOfPlayer: String): Boolean = {
        ListOfPlayers match {
          case head :: tail => IsTheNameUsed(tail, NameOfPlayer)
          case nameOfPlayer :: tail => true
          case Nil => false
        }
      }
    }
  }
}