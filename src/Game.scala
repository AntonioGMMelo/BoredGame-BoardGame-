
//def gameAux(): Unit ={ //Actual Game
//  lazy val PlayerStats : List[PlayerWithOptions] = List() //List of PlayerWithOptions instances
//  @tailrec
//  def generatePlayerStats(Players:List[user.Player]): Unit ={
//    Players.size match { //matches the number of Players in the list to the cases ensuring recursion
//      case _ > 0 => // if there are still Players in the List this case gets activated
//        val player = new PlayerWithOptions(Players.head._1,Players.head._2,(1000,1000),false,false,false,true,true)//Instantiates a Player to a PlayerWithOptions
//        PlayerStats :+ player //adds player to the PlayerStatsList
//        generatePlayerStats(Players.tail) //recursive call
//      case _ => //if there are no more players on the list this case gets activated
//        gameAux2(PlayerStats) //calls the function gameAux2()
//    }
//  }
//  generatePlayerStats(user.Players)//calls generatePlayerStats
//}
//@tailrec
//def gameAux2(PlayerStats:List[PlayerWithOptions]): Unit ={
//  println("Have The Playing Order Be The (i)nsertion Order Or (r)andom") //prompts the user for the playing order
//  val option = scala.io.StdIn.readChar() //reads user's input
//  option match{ //matches user input
//    case 'i' => //if user's input is 'i' this case is activated
//      game(PlayerStats) //calls game()
//    case 'r' => //if users input is 'r' thi case is activated
//     val list= Random.shuffle(PlayerStats) //shuffles the List making the playing order random
//      game(list)//calls game with a shuffled list
//    case _ => //if users input is any other char this case is activated
//      println("Wrong Input Please Restrain Yourself To The Options Listed")//prints this message for the user
//      gameAux2(PlayerStats)//returns player to the start of gameAux2
//  }
//}
//def game(PlayerStats:List[PlayerWithOptions]): Unit ={
//    PlayerStats.size match{
//      case _ > 0 =>
//
//    }
//}

