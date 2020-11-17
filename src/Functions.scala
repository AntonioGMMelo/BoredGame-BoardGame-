import scala.Console.println
import scala.annotation.tailrec
import scala.util.control.Breaks.break
import io.AnsiColor._
import scala.io.AnsiColor
import scala.io.AnsiColor._
import scala.io.Source
import scala.scalax.io._

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

      type Name = String // Name of the Player
      type Color = (String, Boolean) // Color of the Player
      type Player = (Name, Color) // Player type
      val Colors: List[Color] = List((BLACK, false), (WHITE, false), (BLUE, false), (CYAN, false), (RED, false), (GREEN, false), (MAGENTA, false), (YELLOW, false)) // List of all possible selectable Colors
//      val Players: List[Player] = List() // List of all Players

      def CreatePlayer(NewName: Name, NewColor: Int): Unit = { // Creates a player, given a Name, Int and List of Players
        val NewPlayerColor: Color = Colors(NewColor)
        val NewPlayer: Player = (NewName, NewPlayerColor)
        val Players: List[Player] = Source.fromFile("Players.txt").getLines().toList

        IsTheNameUsed(Players, NewName) match {
          case true => println("Player name already exists. Choose another name.")
          case false => {
            val UpdatedPlayerList: List[Player] = UpdatePlayerList(Players, NewPlayer)


            val UpdatedColorList: List[Color] = UpdateColorList(Colors, NewPlayerColor, NewColor)
            println(s"${NewPlayerColor._1}${BOLD}Player created successfully!${RESET}")
            println(UpdatedPlayerList)
            println(Players)
            println(UpdatedColorList)
            println(Colors)
          }
        }
      }

      def EditPlayerName(PreviousName: Name, NewNewName: Name): Unit = { // Edits Player Name, given Previous Name, New Name and List of Players
        val Players: List[Player] = Source.fromFile("Players.txt").getLines().toList
        val EditedPlayer: Player = (NewNewName, Players(Players.indexOf(PreviousName))._2)

        IsTheNameUsed(Players, PreviousName) match {
          case false => println("Player name does not exist. Choose an existent name to be changed.")
          case true =>
            val UpdatedPlayerList: List[Player] = UpdatePlayerList(Players, EditedPlayer)
            println(s"${Players(Players.indexOf(EditedPlayer))._2._1}${BOLD}Player edited successfully!${RESET}")
            UpdatedPlayerList
        }
      }
      def UpdatePlayerList(List: List[Player], Player: Player): List[Player] = { // Updates the List of Players
        val List2: List[Player] = List :+ Player
        List2
      }

      def UpdateColorList(List: List[Color], Color: Color, Index: Int): List[Color] = { // Updates the List of Colors
        val List2: List[Color] =  List.updated(Index, (List(Index)._1, true))
        List2
      }

      @tailrec
      def IsTheNameUsed(ListOfPlayers: List[Player], NameOfPlayer: String): Boolean = { // Checks if a Name is already used by one Player
        ListOfPlayers match {
          case Nil => false
          case _ =>
            ListOfPlayers.head._1 match{
              case NameOfPlayer => true
              case _ => IsTheNameUsed (ListOfPlayers.tail, NameOfPlayer)
            }
        }
      }
    }

