import scala.annotation.tailrec
import scala.io.AnsiColor._
import scala.util.{Try}

object player {
  private val BOUNDARY_LEFT: Int = 0; //Where is the center pixel on the left most spaces
  private val BOUNDARY_RIGHT: Int = 1000; //where the center pixel on the right most spaces are
  private val BOUNDARY_TOP: Int = 0; //Where the center of the top most spaces are
  private val BOUNDARY_BOTTOM: Int = 1000 //Where the center of the bottom most spaces are
  private val dice = List(1, 2, 3, 4, 5, 6) // a six faced dice
  private val wheelItems = List("Price Aint Right Round","Move Back 1 Space", "Move Back 1 Space", "Move Back 1 Space", "Move Back 2 Spaces", "Move Back 2 Spaces", "All Players Move Back 2 Spaces", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 2 Spaces", "Move Forward 2 Spaces", "All Players Move Forwards 2 Spaces", "Go To Jail", "Move Forward 3 Spaces", "Stay", "Roll The dice", "Roll The Weighted Dice") //Wheel options
  private val cards = List("Roll The dice", "Roll The Weighted Dice", "Go To Jail", "Get Out Of Jail Free Card", "50/50", "Skip Question", "Dilate Time") //card options
  private val coin = List("Heads","Tails")
  type feud = (String,List[String])
  type item = (String,Double)
  private val feudEX: feud =("Top 5 Pets",List("Dog","Cat","Rat","Fish","Bird"))
  private val feudList: List[feud]= List(feudEX)
  private val itemEX:item=("Cheese Cake",39.99)
  private val itemList: List[item] = List(itemEX)

  def getSomething(list:List[AnyVal]): AnyVal ={ //gets a random item of a list, use "coin" list for a coin toss use "dice" list for a dice roll or "wheelItems" for a wheel spin or "cards" to draw a card or "feudList" for a feud or "itemList" for an item
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
      case answer if answer.compareTo(aux1)==0 => 3 //best answer means player moves 3 spaces
      case answer if answer.compareTo(aux2)==0 => 2//medium answer means player moves 2 spaces
      case answer if answer.compareTo(aux3)==0 => 2
      case answer if answer.compareTo(aux4)==0 => 1 //acceptable answer mean player moves 1 space
      case answer if answer.compareTo(aux5)==0 => 1
      case _ => 0 //wrong answer no movement for the player
    }
  }
  def priceAintRight(item:item, list:List[Double]): Int ={ //returns the index of the player who was closest
    list match{
      case Nil => -1
      case list => list.indexOf(list.minBy(v => math.abs(v - item._2 ))) //functions that gives the number on the list that is closest to the items price(Inside the indexOF() function)
    }
  }

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

      def weightedDice(): Int = { //a 3 faced dice with the values 2,4 or 6
        lazy val aux = dice.filter(_ < 4) //filters dice to List(1,2,3).
        lazy val aux2 = aux.foldLeft(0)(_+_)//folds aux list to int 6
        lazy val aux3 = dice.filter(_ < 3) //filters dice to List(1,2).
        lazy val aux4 = aux3.map(x => x * 2) //maps dice to List(2,4)
        val diceRoll=aux3 :+ aux2 //concatenates List(2,4) with 6 = List(2,4,6)
        println(diceRoll)
        getSomething(diceRoll).toString.toInt //rolls the dice with the weight
      }
    }


    object user {

      type Name = String // Name of the Player
      type Color = (String, Boolean) // Color of the Player
      type Player = (Name, Color) // Player type

      def CreatePlayer(NewName: Name, NewColor: Int, ListOfPlayers: List[Player], ListOfColors: List[Color]): (List[Player], List[Color]) = { // Creates a Player, given a Name, "Color", list of Player and list of Color
        val NewPlayerColor: Color = ListOfColors(NewColor) // Gets the Color from the Colors list
        val NewPlayer: Player = (NewName, NewPlayerColor) // Creates a new Player based on the input Name and the Color from the line before

        IsTheNameUsed(ListOfPlayers, NewName) match { // Run the function IsTheNameUsed to check if the input Name has already been used
          case true => println("Player name already exists. Choose another name.")
            (ListOfPlayers, ListOfColors) // If the name is used, the output will be the current Player and Color lists
          case false => {
            val UpdatedPlayerList: List[Player] = UpdatePlayerList(ListOfPlayers, NewPlayer, None) // New Player list, which is the previous version plus the new Player created
            val UpdatedColorList: List[Color] = UpdateColorList(ListOfColors, NewPlayerColor, NewColor) // New Color list, which is the previous version plus the update of the Color used
            println(s"${NewPlayerColor._1}${BOLD}Player created successfully!${RESET}") // Print to check the Color and confirm the creation and proper addition to the Player list
            (UpdatedPlayerList, UpdatedColorList) // If the name isn't used, the output will be the the new Player and Color lists
          }
        }
      }

      def EditPlayerName(PreviousName: Name, NewNewName: Name, ListOfPlayers: List[Player]): List[Player] = { // Edits Player Name, given the previous Name, the Name to be changed to and the current Player list
        val EditedPlayer: Player = (NewNewName, ListOfPlayers(ListOfPlayers.indexOf(PreviousName))._2) // Creates a new Player based on the input Name and gets the Color from its creation
        val Index: Int = ListOfPlayers.indexOf((PreviousName, _)) // Index of the Player that wants to change the name

        IsTheNameUsed(ListOfPlayers, PreviousName) match { // Run the function IsTheNameUsed to check if the previous Name has already been used
          case false => println("Player name does not exist. Choose an existent name to be changed.")
            ListOfPlayers // If the name isn't used, the output will be the current Player list
          case true =>
            val UpdatedPlayerList: List[Player] = UpdatePlayerList(ListOfPlayers, EditedPlayer, Some(Index)) // New Player list, which is the previous version plus the new Player created
            println(s"${ListOfPlayers(ListOfPlayers.indexOf(
              EditedPlayer))._2._1}${BOLD}Player edited successfully!${RESET}") // Print to check the Color and confirm the creation and proper addition to the Player list
            UpdatedPlayerList // If the name is used, the output will be the new Player list
        }
      }

      def EditPlayerColor(Player: Player, Color: Int, ListOfPlayers: List[Player], ListOfColors: List[Color]): (List[Player], List[Color]) = {
        val CurrentColor: Color = Player._2
        val NewColor: Color = (ListOfColors(Color)._1, true)
        val EditedPlayer: Player = (Player._1, NewColor)

        val UpdatedPlayerList: List[Player] = UpdatePlayerList(ListOfPlayers, Player, Some(ListOfPlayers.indexOf(Player)))
        val UpdatedColorList: List[Color] = UpdateColorList(ListOfColors, NewColor, Color)
        println(s"${ListOfPlayers(ListOfPlayers.indexOf(EditedPlayer))._2._1}${BOLD}Player edited successfully!${RESET}")
        (UpdatedPlayerList, UpdatedColorList)

      }

      def UpdatePlayerList(List: List[Player], Player: Player, Index: Option[Int]): List[Player] = { // Updates the Player list given a Player list, a Player and an Option[Int]
        Index match{
          case Some(index) =>
            val List2: List[Player] = List.updated(index, Player) // Creates a new Player list by updating a specific index
            List2 // Outputs the new Player list
          case None =>
            val List2: List[Player] = List :+ Player // Creates a new Player list by adding a new Player to the end
            List2 // Outputs the new Player list
        }
      }

      def UpdateColorList(List: List[Color], Color: Color, Index: Int): List[Color] = { // Updates the List of Colors by changing its boolean parameter
        val List2: List[Color] =  List.updated(Index, (List(Index)._1, true)) // Creates a new Color list by updating a specific index
        List2 // Outputs the new Color list
      }

      @tailrec
      def IsTheNameUsed(ListOfPlayers: List[Player], NameOfPlayer: String): Boolean = { // Checks if a Name is already being used by a Player
        ListOfPlayers match { // Match-Case for the input list
          case Nil => false // If the input list is empty, output is false
          case _ => // If the input list isn't empty, proceeds to another Match-Case
            ListOfPlayers.head._1 match{ // Match-Case for the first parameter (Name) of the head of the input list
              case NameOfPlayer => true // If the Name is the same as the input Name, output is true
              case _ => IsTheNameUsed (ListOfPlayers.tail, NameOfPlayer) // Recursive call of the same function
            }
        }
      }
    }