import Menu._
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.shape._
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Board {
  type PlayersExtra=(String,(Int,Int),Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean)
  type feud = (String,List[String]) //feud type
  type item = (String,Double) //Item type

  //loading circles and buttons from fxml File
  @FXML
  var circle1 :Circle =_
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
  var mainmenu:Button=_
  @FXML
  var savegame:Button=_

  def display(players:List[PlayersExtra],questions:List[Pergunta],feuds:List[feud],items:List[item]):Unit = {
  //loading fxml file
    val fxmlLoader = new FXMLLoader(getClass.getResource("Board.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    //Creating stage
    val popUp: Stage = new Stage()
    //Creating Circles List from the fxml load
    var circles: List[Circle]= List(circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8)
    //setting main menu button action
    mainmenu.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        main(Array(""))
        popUp.close()
      }
    })
    //etting savegaem button action
    savegame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        FileFunctions.write_file("Players.txt",FileFunctions.makePlayerString,players)
      }
    })
    //setting endgame button action
    endgame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        popUp.close()
      }
    })
    //setting restartgame button action
    restartgame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        val x:Unit=display(players,questions,feuds,items)
        popUp.close()
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
            circles(i).setFill(javafx.scene.paint.Color.BLUE)
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
      circles(i).setVisible(true)
    }
  }
    //functions that returns the sapce number by taking the diference beetwen the inicial space and th players corinates now and dividing that by 50
    def getSpaceNumber(x:Int,y:Int): Int ={
      ((1050-x/50) + (1050-y/5))
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
      counter -= 1
      for (i <- 0 to players.size) {//players turn in round loop
        if (counter == 0) { //id id the counter hits 0 everyone can move again
          //players(i)._3=true
        }
        if (players(i)._3) {//if player Can Move then
          val rollDaDice:(Int,Boolean) = new RollDaDice().display(players(i)._1,players(i)._4)
          //players(i)._4=rollDaDice._2
          val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward) // player rolls the dice
          //players circle gets moved to new position
          //circles(i).setCenterX(newPos._1)
          //circles(i).setCenterY(newPos._2)
          if (getSpaceNumber(circles(i).getCenterX.toInt, circles(i).getCenterY.toInt) % 3 == 0) {//checks space action if its a multiple of 3 then action is spinthewheel
            val wheel:(String,Boolean)=new SpinDaWheel().display(players(i)._1,players(i)._9)
            //players(i)._9=wheel._2
            val aux:String = wheel._1
            println(aux)
//            aux match {//matches the wheel spin instance to the cases
//            case "Price Aint Right Round" =>
//            val playa:Int=new PriceAintRight().display(players,items) //starts a a price aint right round
//            val newPos:(Int,Int)=player.move(10,50,players(playa)._2,player.moveBackward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//            case "Move Back 1 Space" =>
//            val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveBackward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//            case "Move Back 2 Spaces" =>
//            val newPos:(Int,Int)=player.move(2,50,players(i)._2,player.moveBackward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//            case "All Players Move Back 2 Spaces" =>
//            for(j<-0 to player.size){
//            val newPos:(Int,Int)=player.move(2,50,players(j)._2,player.moveForward)
//            circles(j).setCenterX(newPos._1)
//            circles(j).setCenterY(newPos._2)
//            }
//            case "Move Forward 1 Space" =>
//            val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveForward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//            case "Move Forward 2 Spaces" =>
//            val newPos:(Int,Int)=player.move(2,50,players(i)._2,player.moveForward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//            case "Move Forward 3 Spaces" =>
//            val newPos:(Int,Int)=player.move(3,50,players(i)._2,player.moveForward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//            case "All Players Move Forwards 2 Spaces" =>
//            for(j<-0 to player.size){
//            val newPos:(Int,Int)=player.move(2,50,players(j)._2,player.moveForward)
//            circles(j).setCenterX(newPos._1)
//            circles(j).setCenterY(newPos._2)
//            }
//            case "Roll The dice" =>
              val rollDaDice:(Int,Boolean) = new RollDaDice().display(players(i)._1,players(i)._4)
//            players(i)._4=rollDaDice._2
              val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
            //case "Roll The Weighted Dice" =>
              val rollDaWeightedDice:Int = new RollDaWeightedDice().display(players(i)._1)
              //val newPos:(Int,Int)=player.move(rollDaWeightedDice,50,players(i)._2,player.moveForward)
//            circles(i).setCenterX(newPos._1)
//            circles(i).setCenterY(newPos._2)
//             case "Stay" =>
//             val ugh = new ErrorMessage().display("Unlucky Mate", "YOU SHALL NOT PASS!\n you are not moving of of that wheel spin better luck next time buddy")
//            case "Go To Jail" =>
//            players(i)._3 = false
//             var counter =2
//            }
          } else {
            if (getSpaceNumber(circles(i).getCenterX.toInt, circles(i).getCenterY.toInt) % 2 == 0) { //checks space number if its a multiple of 2 but not of 3 then action is DrawCard
              val card:(String,Boolean)= new DrawCard().display(players(i)._1,players(i)._10)
              //players(i)._10=card._2
              val aux=card._1 //matches the drawn card to the cases
              //aux match{
              //case "Roll The dice" =>
              //val rollDaDice:(Int,Boolean) = new RollDaDice(players(i)._1,players(i)._4)
              //players(i)._4=rollDaDice._2
              //val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward())
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //case "Roll The Weighted Dice" =>
              //val rollDaWeightedDice:Int = new RollDaWeightedDice(players(i)._1)
              //val newPos:(Int,Int)=player.move(rollDaWeightedDice,50,players(i)._2,player.moveForward())
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //case "Go To Jail" =>
              //players(i)._3 = false //sets can move to false
              // var counter =2 //sets counter to 2
              //case "50/50" =>
              //players(i)._7=true //gives player the abillity to ask for a 50/50 while answering a question
              //case "Skip Question"=>
              //players(i)._6=true//gives player the abillity to ask for a new Question while answering a question
              //case "Dilate Time" =>
              //players(i)._8=true//gives player the abillity to ask for more time while answering a question
              //case "Feud Round"=>
              //val Spaces Int = new Feud().display(players(i)._1,feuds) //Starts a feud round
              //val newPos:(Int,Int)=player.move(Spaces,50,players(i)._2,player.moveForward())
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //}
            } else { //if the space number is not a multiple of 2 or 3 then action si answer question
              //val move:(Boolean,Boolean,Boolean,Boolean)=new Question().display(questions,players(i)._1,players(i)._6,players(i)._7,players(i)._8)
              //players(i)._6= move._2
              //players(i)._7= move._3
              //players(i)._8= move._4
              //if(move._1){ //if the player got the answer right move._1=true moves forward 1 space else moves backward 1 space
              //val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveForward)
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //}else{
              //val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveBackward)
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //}
            }
          }
        }
        if(players(i)._2._1==1050 && players(i)._2._1==1050 && canWin!=0){ //checks if the player won by checking their Positon and if its not the first round
          //val winnerQuote :String = new WinnerQuote.display(players(i)._1) //asks the winner for a quote
          //val whatever= new ErrorMessage("WINNER IS" + players(i)._1 + "In "+ canWin+1 + " Rounds",players(i)._1+"SAYS: " + winnerQuote) //display winner's quote
          someOneWon=true //changes var to true to "break" the while loop
        }
      }
      canWin+=1 //counts the rounds elapsed
    }
    //create scene and set stage's scene to the scene created
      val scene: Scene= new Scene(mainViewRoot)
      popUp.setScene(scene)
      popUp.showAndWait()
  }
}
