import scala.annotation.tailrec
import scala.io.AnsiColor._
import scala.util.{Try}

object user {

  type Name = String // Name of the Player
  type Color = (String, Boolean) // Color of the Player
  type Player = (Name, Color) // Player type

  def CreatePlayer(NewName: Name, NewColor: String, ListOfPlayers: List[Player], ListOfColors: List[Color]): (List[Player], List[Color]) = { // Creates a Player, given a Name, "Color", list of Player and list of Color
    val NewPlayerColor: Color = ListOfColors(ListOfColors.indexOf(NewColor)) // Gets the Color from the Colors list
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
    val EditedPlayer: Player = (NewNewName, ListOfPlayers(ListOfPlayers.indexWhere(_._1 == (PreviousName)))._2) // Creates a new Player based on the input Name and gets the Color from its creation
    val Index: Int = ListOfPlayers.indexOf((PreviousName, ListOfPlayers(ListOfPlayers.indexWhere(_._1 == PreviousName))._2)) // Index of the Player that wants to change the name

    IsTheNameUsed(ListOfPlayers, PreviousName) match { // Run the function IsTheNameUsed to check if the previous Name has already been used
      case false => println("Player name does not exist. Choose an existent name to be changed.")
        ListOfPlayers // If the name isn't used, the output will be the current Player list
      case true =>
        val UpdatedPlayerList: List[Player] = UpdatePlayerList(ListOfPlayers, EditedPlayer, Some(Index)) // New Player list, which is the previous version plus the new Player created
              println(UpdatedPlayerList)
              println(s"${
              (UpdatedPlayerList(UpdatedPlayerList.indexWhere(_._1 ==
                EditedPlayer._1))._2)._1
        }${BOLD}Player edited successfully!${RESET}") // Print to check the Color and confirm the creation and proper addition to the Player list
        UpdatedPlayerList // If the name is used, the output will be the new Player list
    }
  }

  def EditPlayerColor(Player: Player, Color: Int, ListOfPlayers: List[Player], ListOfColors: List[Color]): (List[Player], List[Color]) = {
    val CurrentColor: Color = Player._2
    val NewColor: Color = (ListOfColors(Color)._1, true)
    val EditedPlayer: Player = (Player._1, NewColor)

    val UpdatedPlayerList: List[Player] = UpdatePlayerList(ListOfPlayers, Player, Some(ListOfPlayers.indexOf(Player)))
    val UpdatedColorList: List[Color] = UpdateColorList(ListOfColors, NewColor, Color)
    println(s"${(UpdatedPlayerList(UpdatedPlayerList.indexWhere(_._1 ==
      EditedPlayer._1))._2)._1}${BOLD}Player edited successfully!${RESET}")
    (UpdatedPlayerList, UpdatedColorList)

  }

  def UpdatePlayerList(List: List[Player], Player: Player, Index: Option[Int]): List[Player] = { // Updates the Player list given a Player list, a Player and an Option[Int]
    Index match {
      case Some(index) =>
        val List2: List[Player] = List.updated(index, Player) // Creates a new Player list by updating a specific index
        List2 // Outputs the new Player list
      case None =>
        val List2: List[Player] = List :+ Player // Creates a new Player list by adding a new Player to the end
        List2 // Outputs the new Player list
    }
  }

  def UpdateColorList(List: List[Color], Color: Color, Index: Int): List[Color] = { // Updates the List of Colors by changing its boolean parameter
    val List2: List[Color] = List.updated(Index, (List(Index)._1, true)) // Creates a new Color list by updating a specific index
    List2 // Outputs the new Color list
  }

  @tailrec
  def IsTheNameUsed(ListOfPlayers: List[Player], NameOfPlayer: String): Boolean = { // Checks if a Name is already being used by a Player
    ListOfPlayers match { // Match-Case for the input list
      case Nil => false // If the input list is empty, output is false
      case _ => // If the input list isn't empty, proceeds to another Match-Case
        ListOfPlayers.head._1 match { // Match-Case for the first parameter (Name) of the head of the input list
          case NameOfPlayer => true // If the Name is the same as the input Name, output is true
          case _ => IsTheNameUsed(ListOfPlayers.tail, NameOfPlayer) // Recursive call of the same function
        }
    }
  }


}

