import scala.annotation.tailrec
import scala.io.AnsiColor
import scala.io.AnsiColor._
import scala.util.Try
import player._

object user {

  type Name = String // Name of the Player
  type Color = (String, Boolean) // Color of the Player
  type Player = (Name, Color) // Player type
  type PlayerExtra = (String, (Int, Int), Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean)

  def CreatePlayer(Color: String, ListOfPlayers: List[PlayerExtra], ListOfColors: List[Color]): (List[PlayerExtra], List[Color]) = { // Creates a Player, given a Name, "Color", list of Player and list of Color
    val NewPlayerColor: Color = (Color, true) //ListOfColors(ListOfColors.indexOf(NewColor)) // Gets the Color from the Colors list
    val NewPlayer: PlayerExtra = new PlayerExtra(Color, (1050, 1050), true, true, true, true, true, true, true, true) // Creates a new Player based on the input Name and the Color from the line before

    val UpdatedPlayerList: List[PlayerExtra] = UpdatePlayerList(ListOfPlayers, NewPlayer, None) // New Player list, which is the previous version plus the new Player created
    val UpdatedColorList: List[Color] = UpdateColorList(ListOfColors, NewPlayerColor, ListOfColors.indexWhere(_._1 == Color)) // New Color list, which is the previous version plus the update of the Color used
    println(s"${NewPlayerColor._1}${BOLD}Player created successfully!${RESET}") // Print to check the Color and confirm the creation and proper addition to the Player list
    (UpdatedPlayerList, UpdatedColorList) // If the name isn't used, the output will be the the new Player and Color lists

  }

  def EditPlayer(oldColor: String, newColor: String, ListOfPlayers: List[PlayerExtra], ListOfColors: List[Color]): (List[PlayerExtra], List[Color]) = {
    val CurrentColor: Color = ListOfColors(ListOfColors.indexWhere(_._1 == oldColor))
    val NewColor: Color = (newColor, true)
    val OgPlayer: PlayerExtra = ListOfPlayers(ListOfPlayers.indexWhere(_._1 == oldColor))
    val EditedPlayer: PlayerExtra = new PlayerExtra(NewColor._1, OgPlayer._2, OgPlayer._3, OgPlayer._4, OgPlayer._5, OgPlayer._6, OgPlayer._7, OgPlayer._8, OgPlayer._9, OgPlayer._10)

    val UpdatedPlayerList: List[PlayerExtra] = UpdatePlayerList(ListOfPlayers, EditedPlayer, Some(ListOfPlayers.indexWhere(_._1 == OgPlayer._1)))
    val UpdatedColorList: List[Color] = UpdateColorList(ListOfColors, NewColor, ListOfColors.indexOf(CurrentColor))
    println(s"${
      (UpdatedPlayerList(UpdatedPlayerList.indexWhere(_._1 ==
        EditedPlayer._1))._2)._1
    }${BOLD}Player edited successfully!${RESET}")
    (UpdatedPlayerList, UpdatedColorList)
  }

  @tailrec
  def isFeudUsed(inputFeud: feud, feudList: List[feud]): Boolean = {

    feudList match {
      case Nil => false
      case x :: t => {
        if (x == inputFeud)
          true
        else isFeudUsed(inputFeud, t)
      }
    }
  }

  def printoutCurrying(s: String)(i: Int): Unit = { // CURRYING
    println("Message: " + s + " , Number: " + i)
  }

  def AddFeud(inputFeud: feud, feudList: List[feud]): List[feud] = { //concat new feud to list
    val result: List[feud] = feudList :+ inputFeud

    isFeudUsed(inputFeud, feudList) match {
      case true => val help = printoutCurrying("Feud already exists") _
        val randomInt = scala.util.Random
        help(randomInt.nextInt())
        feudList
      case false => val help = printoutCurrying("Feud created") _
        val randomInt = scala.util.Random
        help(randomInt.nextInt())
        result
    }
  }

  def DeleteFeud(inputFeud: feud, feudList: List[feud]): List[feud] = { //delete feud from list
    val result: List[feud] = feudList diff List(inputFeud)

    isFeudUsed(inputFeud, feudList) match {
      case true => val help = printoutCurrying("Feud deleted") _
        val randomInt = scala.util.Random
        help(randomInt.nextInt())
        result
      case false => val help = printoutCurrying("Feud inexistent") _
        val randomInt = scala.util.Random
        help(randomInt.nextInt())
        feudList
    }
  }

  @tailrec
  def isItemUsed(inputItem: item, itemList: List[item]): Boolean = {

    itemList match {
      case Nil => false
      case x :: t => {
        if (x == inputItem)
          true
        else isItemUsed(inputItem, t)
      }
    }
  }

  def AddItem(inputItem: item, itemList: List[item]): List[item] = { //concat new priceaintright item to list
    val result: List[item] = itemList :+ inputItem
    val help = printoutCurrying("Item added") _
    val randomInt = scala.util.Random
    help(randomInt.nextInt())
    result
  }

  def DeleteItem(inputItem: item, itemList: List[item]): List[item] = { //delete priceaintright item from list
    val result: List[item] = itemList diff List(inputItem)

    isItemUsed(inputItem, itemList) match {
      case true => val help = printoutCurrying("Item deleted") _
        val randomInt = scala.util.Random
        help(randomInt.nextInt())
        result
      case false => val help = printoutCurrying("Item inexistent") _
        val randomInt = scala.util.Random
        help(randomInt.nextInt())
        itemList
    }
  }

  def UpdatePlayerList(List: List[PlayerExtra], Player: PlayerExtra, Index: Option[Int]): List[PlayerExtra] = { // Updates the Player list given a Player list, a Player and an Option[Int]
    Index match {
      case None =>
        val List2: List[PlayerExtra] = List :+ Player // Creates a new Player list by adding a new Player to the end
        List2 // Outputs the new Player list

      case Some(index) =>
        val List2: List[PlayerExtra] = List.updated(index, Player) // Creates a new Player list by updating a specific index
        println(List2)
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