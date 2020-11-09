import scala.annotation.tailrec

def mainMenuOptions(): Unit ={
  println("(a)dd player")
  println("(e)dit player")  //prints the main menu Options
  println("(s)tart game")
  println("(q)uit")
}
def mainMenu(): Unit ={
  mainMenuOptions()  //calls mainMenu()
  val option =scala.io.StdIn.readChar() //Reads Input from user
  option match{ //matches user input
    case 'a' => //if user inputted 'a' this case is activated
        user.Players.size match { //Checks the amount o players already created
          case _ < 8 => //if there are less than 8 players this case gets activated
            @tailrec
            def printAvailableColors(Colors:List[(Int,Boolean)]): Unit ={ //prints all the available colors by checking the Boolean in the list true if the color has been used and false if it hasn't
              Colors.size match{ //matches the number of colors in the list to the cases ensuring recursion
                case _ > 0 => // if there are still colors in the List this case gets activated
                  Colors.head._2 match { //Matches the boolean to see weather the color is still unused
                    case false => //if the color hasn't been used this case gets activated
                      println(Colors.head._1) //the color is printed as an option for the user
                      printAvailableColors(Colors.tail) //recursive call
                    case _ => //if the color has been used this case gets activated
                      printAvailableColors(Colors.tail) //recursive call
                  }
                case _ => //if there are no colors left in the list this case is activated
                  println("Type Player Name And Color") //prompts the user for a name and a color
              }
            }
            val name = scala.io.StdIn.readLine() //Reads name input from user
            val color = scala.io.StdIn.readInt() //reads color input from user
            user.CreatePlayer(name,color) //calls CreatePlayer with the user inputs
            mainMenu() //re-sends player to the start
          case _ => //if there are 8 players this case is activated
            println("Maximum Number Of Players Reached") //Prints error message
            mainMenu()//re-sends the user to the start
        }
    case 'e' => //if user input is 'e' this case is activated
      user.Players match { //matches weather there are any players to be edited
        case nil => //if there are no players this case is activated
          println("No Players To Edit") //prints error message
          mainMenu() //re-sends the user to the start
        case _ => //if there are players to edit this case is activated
          @tailrec
          def printAvailablePlayers(Players: List[user.Player]): Unit = { //prints all the available Players
            Players.size match { //matches the number of Players in the list to the cases ensuring recursion
              case _ > 0 => // if there are still Players in the List this case gets activated
                println(Players.head._1) //the player is printed as an option for the user
                printAvailablePlayers(Players.tail) //recursive call
              case _ => //if there are no more players on the list this case gets activated
                println("Type Player Name") //prompts the user for a player's name
            }
            val playerToEdit = scala.io.StdIn.readLine() //reads user input for the previous name of the player
            println("Type New Player Name") //prompts user for the new name for the player
            val playerNewName = scala.io.StdIn.readLine() //reads user input for the new player name
            user.EditPlayerName(playerToEdit, playerNewName) //calls EditPlayerName with user's inputs
            mainMenu() //re-sends the user to the start
          }
        case 's' => //if the user input is 's' this case gets activated
          user.Players.size match{ //matches the number of players to the case
            case _ >= 2 => //if there are 2 or more players this case is activated
              game() //starts the game
            case _ => //if there are less than 2 payers this case gets activated
              println("Not Enough Players to Start Game") //prints  the error message
              mainMenu() //re-sends player to the top
          }
        case 'q' => //if the user's input is 'q' this case is activated
          println("Hope You Had Fun, Come Back Soon") //prints this message for the user
        case _ => //case the input is not accepted this case gets activated
          println("Wrong Input Please Restrain Yourself To The Options Listed")//prints this message for the user
          mainMenu()//re-sends the user to the start
      }
  }
}
def game(): Unit ={ //Actual Game

}