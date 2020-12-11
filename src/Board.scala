
import Menu._
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.Button
import javafx.scene.shape._
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Board {
  type PlayersExtra=(String,(Int,Int),Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean)

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
  def display(players:List[PlayersExtra]):Unit = {

    val fxmlLoader = new FXMLLoader(getClass.getResource("Board.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    //    val rect = new Rectangle()
    val popUp: Stage = new Stage()
    var circles: List[Circle]= List(circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8)

    mainmenu.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        main(Array(""))
        popUp.close()
      }
    })
    savegame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        //writePlayers
      }
    })
    endgame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        popUp.close()
      }
    })
    restartgame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        val x:Unit=display(players)
        popUp.close()
      }
    })
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
    def getSpaceNumber(x:Int,y:Int): Int ={
      ((1050-x/50) + (1050-y/5))
    }
      //game begins
      var counter =0
    //round loop
    var someOneWon=false
    var canWin=0
    while(!someOneWon) {
      counter -= 1
      for (i <- 0 to players.size) {
        if (counter == 0) {
          //players(i)._3=true
        }
        if (players(i)._3) {
          //val rollDaDice:(Int,Boolean) = new RollDaDice(players(i)._1,players(i)._4)
          //players(i)._4=rollDaDice._2
          //val newPos:(Int,Int)=player.move(rollDaDice._1,50,players(i)._2,player.moveForward())
          //circles(i).setCenterX(newPos._1)
          //circles(i).setCenterY(newPos._2)
          if (getSpaceNumber(circles(i).getCenterX.toInt, circles(i).getCenterY.toInt) % 3 == 0) {
            //val wheel:(String,Boolean)=new spinDaWheel().display(players(i)._1,players(i)._9)
            //players(i)._9=wheel._2
            //val aux:String = wheel._1
            //aux match {
            //case "Price Aint Right Round" =>
            //val playa:Int=new priceAintRight().display(players)
            //val newPos:(Int,Int)=player.move(10,50,players(playa)._2,player.moveBackward())
            //circles(i).setCenterX(newPos._1)
            //circles(i).setCenterY(newPos._2)
            //case "Move Back 1 Space" =>
            //val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveBackward())
            //circles(i).setCenterX(newPos._1)
            //circles(i).setCenterY(newPos._2)
            //case "Move Back 2 Spaces" =>
            //val newPos:(Int,Int)=player.move(2,50,players(i)._2,player.moveBackward())
            //circles(i).setCenterX(newPos._1)
            //circles(i).setCenterY(newPos._2)
            //case "All Players Move Back 2 Spaces" =>
            //for(j<-0 to player.size){
            //val newPos:(Int,Int)=player.move(2,50,players(j)._2,player.moveForward())
            //circles(j).setCenterX(newPos._1)
            //circles(j).setCenterY(newPos._2)
            //}
            //case "Move Forward 1 Space" =>
            //val newPos:(Int,Int)=player.move(1,50,players(i)._2,player.moveForward())
            //circles(i).setCenterX(newPos._1)
            //circles(i).setCenterY(newPos._2)
            //case "Move Forward 2 Spaces" =>
            //val newPos:(Int,Int)=player.move(2,50,players(i)._2,player.moveForward())
            //circles(i).setCenterX(newPos._1)
            //circles(i).setCenterY(newPos._2)
            //case "Move Forward 3 Spaces" =>
            //val newPos:(Int,Int)=player.move(3,50,players(i)._2,player.moveForward())
            //circles(i).setCenterX(newPos._1)
            //circles(i).setCenterY(newPos._2)
            //case "All Players Move Forwards 2 Spaces" =>
            //for(j<-0 to player.size){
            //val newPos:(Int,Int)=player.move(2,50,players(j)._2,player.moveForward())
            //circles(j).setCenterX(newPos._1)
            //circles(j).setCenterY(newPos._2)
            //}
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
            // case "Stay" =>
            // val ugh = new ErrorMessage().display("Unlucky Mate", "YOU SHALL NOT PASS!\n you are not moving of of that wheel spin better luck next time buddy")
            //case "Go To Jail" =>
            //players(i)._3 = false
            // var counter =2
            //}
          } else {
            if (getSpaceNumber(circles(i).getCenterX.toInt, circles(i).getCenterY.toInt) % 2 == 0) {
              //, , ","Feud Round","Feud Round","Feud Round","Feud Round","Feud Round"
              //val card:(String,Boolean)= new drawCard().display(players(i)._1,players(i)._10)
              //players(i)._10=card._2
              //val aux=card._1
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
              //players(i)._3 = false
              // var counter =2
              //case "50/50" =>
              //players(i)._7=true
              //case "Skip Question"=>
              //players(i)._6=true
              //case "Dilate Time" =>
              //players(i)._8=true
              //case "Feud Round"=>
              //val Spaces Int = new FeudDisplay().display(players(i)._1)
              //val newPos:(Int,Int)=player.move(Spaces,50,players(i)._2,player.moveForward())
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //}
            } else {
              //val move:(Boolean,Boolean,Boolean,Boolean)=new Question().display(players(i)._1,players(i)._6,players(i)._7,players(i)._8)
              //players(i)._6= move._2
              //players(i)._7= move._3
              //players(i)._8= move._4
              //if(move._1){
              //val newPos:(Int,Int)=player.move(actionScreen,50,players(i)._2,player.moveForward())
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //}else{
              //val newPos:(Int,Int)=player.move(actionScreen,50,players(i)._2,player.moveBackward())
              //circles(i).setCenterX(newPos._1)
              //circles(i).setCenterY(newPos._2)
              //}
            }
          }
        }
        if(players(i)._2._1==1050 && players(i)._2._1==1050 && canWin!=0){
          //val winnerQuote :String = new WinnerQuote.display(players(i)._1)
          //val whatever= new ErrorMessage("WINNER IS" + players(i)._1,players(i)._1+"SAYS: " + winnerQuote)
          someOneWon=true
        }
      }
      canWin+=1
    }


      val scene: Scene= new Scene(mainViewRoot)
      popUp.setScene(scene)
      popUp.showAndWait()
  }
}
