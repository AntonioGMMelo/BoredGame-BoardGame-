import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.shape._
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage


class Board {
  type PlayersExtra=(String,(Int,Int),Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean)//PlayerExtra(color,(posX,posY),canMove,canReRolDice,canWeighDice,canSkipQuestion,can50/50,canGetQuestionRight,canReSpinTheWheel,canDrawNewCard)
  type feud = (String,List[String]) //feud type
  type item = (String,Double) //Item type

  //loading circles and buttons from fxml File
  @FXML
  var Circle1 :Circle =_
  @FXML
  var circle2 :Circle =_
  @FXML
  var circle3 :Circle =_
  @FXML
  var circle4 :Circle =_
  @FXML
  var circle5 :Circle =_
  @FXML
  var circle6 :Circle =_
  @FXML
  var circle7 :Circle =_
  @FXML
  var circle8 :Circle =_
  @FXML
  var endgame:Button=_
  @FXML
  var restartgame:Button=_
  @FXML
  var closegame:Button=_
  @FXML
  var savegame:Button=_
  @FXML
  var StartGame:Button=_


  def updatePlayerList(player:PlayersExtra,list: List[PlayersExtra]):List[PlayersExtra]={
      var newList= list.updated(list.indexWhere(_._1==player._1),player)
      newList
  }
  def startGame(): Unit ={
    whatver()
    StartGame.setVisible(false)
  }

  def whatver(){
    var Players:List[PlayersExtra]=FileFunctions.read_file("Players.txt",FileFunctions.makePlayer)
    var questions:List[Pergunta]=FileFunctions.read_file("Questions.txt",FileFunctions.makePergunta)
    var feuds:List[feud]=FileFunctions.read_file("Feuds.txt",FileFunctions.makeFeud)
    var items:List[item]=FileFunctions.read_file("Items.txt",FileFunctions.makeItem)
    var players = Players
    var circles: List[Circle] = List(Circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8)
    //setting main menu button action
    closegame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        Circle1.getScene().getWindow.hide()
      }
    })
    //etting savegaem button action
    savegame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        FileFunctions.write_file("Players.txt",FileFunctions.makePlayerString,players)
        new ErrorMessage().display("Warning","Game Saved")
      }
    })
    //setting endgame button action
    endgame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        for(x<-0 to players.size-1){
          players=updatePlayerList(new PlayersExtra(players(x)._1,(1050,1050),true,true,true,true,true,true,true,true),players)
        }
        FileFunctions.write_file("Players.txt",FileFunctions.makePlayerString,players)
        Circle1.getScene().getWindow.hide()
      }
    })
    //setting restartgame button action
    restartgame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        for(x<-0 to players.size-1){
          players=updatePlayerList(new PlayersExtra(players(x)._1,(1050,1050),true,true,true,true,true,true,true,true),players)
          circles(x).setLayoutX(1050)
          circles(x).setLayoutY(1050)
        }
        FileFunctions.write_file("Players.txt",FileFunctions.makePlayerString,players)
      }
    })
    //setting circles to correspond to a player
  for(i<-0 to players.size-1){
    if(!(players(i).equals("NO"))) {
      if (players(i)._1.equals("BLACK")) {
        circles(i).setFill(javafx.scene.paint.Color.BLACK)
      } else {
        if (players(i)._1.equals("WHITE")) {
          circles(i).setFill(javafx.scene.paint.Color.WHITE)
        } else {
          if (players(i)._1.equals("BLUE")) {
            circles(i).setFill(javafx.scene.paint.Color.LIGHTBLUE)
          } else {
            if (players(i)._1.equals("CYAN")) {
              circles(i).setFill(javafx.scene.paint.Color.CYAN)
            } else {
              if (players(i)._1.equals("RED")) {
                circles(i).setFill(javafx.scene.paint.Color.RED)
              } else {
                if (players(i)._1.equals("GREEN")) {
                  circles(i).setFill(javafx.scene.paint.Color.GREEN)
                } else {
                  if (players(i)._1.equals("MAGENTA")) {
                    circles(i).setFill(javafx.scene.paint.Color.MAGENTA)
                  } else {
                    if (players(i)._1.equals("YELLOW")) {
                      circles(i).setFill(javafx.scene.paint.Color.YELLOW)
                    }
                  }
                }

              }
            }
          }
        }
      }
      circles(i).setLayoutX(players(i)._2._1)
      circles(i).setLayoutY(players(i)._2._2)
      circles(i).setVisible(true)
    }
  }
    //functions that returns the sapce number by taking the diference beetwen the inicial space and th players corinates now and dividing that by 50
    def getSpaceNumber(x:Int,y:Int): Int ={
      (((1050-x)/50) + ((1050-y)/50))
    }
      //game begins
    //var to help Un Jailing a player that is jailed
      var counter =0
    //var that helps now if someone has won and the game shall end
    var someOneWon=false
    //var that allows player to only win after the first round seing as the win condition is beeign at the starting space again
    var canWin=0
    //round loop
    while(!someOneWon) {
      //takes counter down to say 1 less round that the player that cant move needs to wait untill beeing able to move
      counter= counter -1
      for (i <- 0 to players.size-1) {//players turn in round loop
        if (counter <= 0) { //id id the counter hits 0 everyone can move again
          players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,true,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
        }
        val oldPosition:(Int,Int)=players(i)._2//players original position
        if (players(i)._3) {//if player Can Move then
          val rollDaDice:(Int,Boolean) = new RollDaDice().display(players(i)._1,players(i)._4)
          val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward) // player rolls the dice
          players= updatePlayerList(new PlayersExtra(players(i)._1,(newPos._1,newPos._2),players(i)._3,rollDaDice._2,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
          //players circle gets moved to new position
          circles(i).setLayoutX(newPos._1)
          circles(i).setLayoutY(newPos._2)
          val a=getSpaceNumber(circles(i).getLayoutX.toInt, circles(i).getLayoutY.toInt)
          if (a % 3 == 0) {//checks space action if its a multiple of 3 then action is spinthewheel
            val wheel:(String,Boolean)=new SpinDaWheel().display(players(i)._1,players(i)._9)
            players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,wheel._2,players(i)._10),players)
            val aux:String = wheel._1
            aux match {//matches the wheel spin instance to the cases
            case "Price Aint Right Round" =>
              val playa:Int=new PriceAintRight().display(players,items) //starts a a price aint right round
              val newPos:(Int,Int)=player.move(10,50,players(playa)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(playa)._1,newPos,players(playa)._3,players(playa)._4,players(playa)._5,players(playa)._6,players(playa)._7,players(playa)._8,players(playa)._9,players(playa)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "Move Back 1 Space" =>
              val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveBackward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,(newPos._1,newPos._2),players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "Move Back 2 Spaces" =>
              val newPos:(Int,Int)=player.move(2,50,players(i)._2,player.moveBackward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,(newPos._1,newPos._2),players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "All Players Move Back 2 Spaces" =>
              for(j<-0 to players.size-1){
                val newPos:(Int,Int)=player.move(2,50,players(j)._2,player.moveBackward)
                players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
                circles(j).setLayoutX(newPos._1)
                circles(j).setLayoutY(newPos._2)
              }
            case "Move Forward 1 Space" =>
              val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "Move Forward 2 Spaces" =>
              val newPos:(Int,Int)=player.move(2,50,players(i)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "Move Forward 3 Spaces" =>
              val newPos:(Int,Int)=player.move(3,50,players(i)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "All Players Move Forwards 2 Spaces" =>
            for(j<-0 to players.size-1){
              val newPos:(Int,Int)=player.move(2,50,players(j)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(j).setLayoutX(newPos._1)
              circles(j).setLayoutY(newPos._2)
            }
            case "Roll The dice" =>
              val rollDaDice:(Int,Boolean) = new RollDaDice().display(players(i)._1,players(i)._4)
              val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,rollDaDice._2,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "Roll The Weighted Dice" =>
              val rollDaWeightedDice:Int = new RollDaWeightedDice().display(players(i)._1)
              val newPos:(Int,Int)=player.move(rollDaWeightedDice,50,players(i)._2,player.moveForward)
              players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
            case "Stay" =>
              val ugh = new ErrorMessage().display("Unlucky Mate", "YOU SHALL NOT PASS!\n you are not moving of of that wheel spin better luck next time buddy")
            case "Go To Jail" =>
              players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,false,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
             var counter =1
              val ugh = new ErrorMessage().display("Unlucky Mate", "Can't play for one rounds")
            }
          } else {
            if (a % 2 == 0) { //checks space number if its a multiple of 2 but not of 3 then action is DrawCard
              val card:(String,Boolean)= new DrawCard().display(players(i)._1,players(i)._10)
              players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,players(i)._3,rollDaDice._2,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,card._2),players)
              val aux= card._1 //matches the drawn card to the cases
              aux match{
              case "Roll The dice" =>
                val rollDaDice:(Int,Boolean) = new RollDaDice().display(players(i)._1,players(i)._4)
                val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward)
                players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,rollDaDice._2,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
                circles(i).setLayoutX(newPos._1)
                circles(i).setLayoutY(newPos._2)
              case "Roll The Weighted Dice" =>
                val rollDaWeightedDice:Int = new RollDaWeightedDice().display(players(i)._1)
                val newPos:(Int,Int)=player.move(rollDaWeightedDice,50,players(i)._2,player.moveForward)
                players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
                circles(i).setLayoutX(newPos._1)
                circles(i).setLayoutY(newPos._2)
              case "Go To Jail" =>
                players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,false,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players) //sets can move to false
               var counter =1 //sets counter to 2
                val ugh = new ErrorMessage().display("Unlucky Mate", "Can't play for one rounds")
              case "50/50" =>
                players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,players(i)._3,players(i)._4,players(i)._5,players(i)._6,true,players(i)._8,players(i)._9,players(i)._10),players)//gives player the abillity to ask for a 50/50 while answering a question
              val ugh = new ErrorMessage().display("Lucky Mate", "You Can Now Ask For a Tip While Answering a Question If You Could not before")
              case "Skip Question"=>
                //gives player the abillity to ask for a new Question while answering a question
                players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,players(i)._3,players(i)._4,players(i)._5,true,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
                val ugh = new ErrorMessage().display("Lucky Mate", "You Can Now Ask For a New Question While Answering a Question If You Could not before")
              case "Get Right" =>
                players= updatePlayerList(new PlayersExtra(players(i)._1,players(i)._2,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,true,players(i)._9,players(i)._10),players)//gives player the abillity to ask for more time while answering a question
              val ugh = new ErrorMessage().display("Lucky Mate", "You Can Now Get The Question Right While Answering a Question If You Could not before")
              case "Feud Round"=>
              val Spaces :Int = new Feud().display(players(i)._1,feuds) //Starts a feud round
              val newPos:(Int,Int)=player.move(Spaces,50,players(i)._2,player.moveForward)
                players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
              circles(i).setLayoutX(newPos._1)
              circles(i).setLayoutY(newPos._2)
              }
            } else { //if the space number is not a multiple of 2 or 3 then action si answer question
               val move:(Boolean,Boolean,Boolean,Boolean)=new Question().display(Tema("yikes",questions),players(i)._1,(players(i)._6,players(i)._7,players(i)._8))
                val a= move._2
                val b= move._3
                val c=move._4

              if(move._1){ //if the player got the answer right move._1=true moves forward 1 space else moves backward 1 space
                  val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveForward)
                  players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,a,b,c,players(i)._9,players(i)._10),players)
                  circles(i).setLayoutX(newPos._1)
                  circles(i).setLayoutY(newPos._2)
              }else{
                  val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveBackward)
                  players= updatePlayerList(new PlayersExtra(players(i)._1,newPos,players(i)._3,players(i)._4,players(i)._5,a,b,c,players(i)._9,players(i)._10),players)
                  circles(i).setLayoutX(newPos._1)
                  circles(i).setLayoutY(newPos._2)
              }
            }
          }
        }
        if(oldPosition._2<1050 && oldPosition._1==1050 && players(i)._2._2==1050 && canWin!=0){ //checks if the player won by checking their originalPositon and their new Positon and if its not the first round
          players= updatePlayerList(new PlayersExtra(players(i)._1,(1050,1050),players(i)._3,players(i)._4,players(i)._5,players(i)._6,players(i)._7,players(i)._8,players(i)._9,players(i)._10),players)
          circles(i).setLayoutX(1050)
          circles(i).setLayoutY(1050)
          val winnerQuote :String = new WinnerQuote().display(players(i)._1) //asks the winner for a quote
          val help =("WINNER IS" + players(i)._1 + "In "+ canWin+1 + " Rounds")
          val help2=(players(i)._1+"SAYS: " + winnerQuote)
          val whatever= new ErrorMessage().display(help,help2) //display winner's quote
          someOneWon=true //changes var to true to "break" the while loop
        }
      }
      canWin+=1 //counts the rounds elapsed
    }
  }
}
