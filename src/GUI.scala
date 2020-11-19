import scala.annotation.tailrec
import scala.io.AnsiColor._
import javafx.application._
import javafx._
import javafx.fxml.FXMLLoader
import javafx.scene._
import javafx.stage._
import javafx.geometry._
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.VBox


class GUI extends Application{
  type Name = String // Name of the Player
  type Color = (String, Boolean) // Color of the Player
  type Player = (Name, Color) // Player type

  var Players:List[Player]=List()
  var Questions:List[Pergunta.type ]=List()
  var Themes:List[Tema.type ]=List()
  var Colors:List[Color]=List((BLACK, false), (WHITE, false), (BLUE, false), (CYAN, false), (RED, false), (GREEN, false), (MAGENTA, false), (YELLOW, false))

  override def start(primaryStage: Stage): Unit ={
    //titling the stage as Main Menu
    primaryStage.setTitle("Main Menu")
    //loading  fmxl
    val fxmlLoader = new FXMLLoader(getClass.getResource("controller.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating buttons
    //Create Player Button setup
    val CreatePlayer : Button = new Button("Create Player")
    CreatePlayer.setOnAction(e ->{
      val layout : VBox = new VBox(10)
      Players.size match { //Checks the amount o players already created
        case _ < 8 => //if there are less than 8 players this case gets activated
          @tailrec
          def listAvailableColors(Colors:List[(String,Boolean)]): Unit = { //prints all the available colors by checking the Boolean in the list true if the color has been used and false if it hasn't
            Colors.size match { //matches the number of colors in the list to the cases ensuring recursion
              case _ > 0 => // if there are still colors in the List this case gets activated
                Colors.head._2 match { //Matches the boolean to see weather the color is still unused
                  case false => //if the color hasn't been used this case gets activated

                    val label: Label = new Label()
                    label.setText(Colors.head._1)
                    layout.getChildren().add(label)//the color is added as an option for the user
                    listAvailableColors(Colors.tail) //recursive call
                  case _ => //if the color has been used this case gets activated
                    listAvailableColors(Colors.tail) //recursive call
                }
              case _ => //if there are no colors left in the list this case is activated
            }
          }
          listAvailableColors(Colors)//calls printAvailableColors
          val ColorAndName: (String,String) = CreatePlayer.display(layout)
          val auxiliar:(List[Player], List[Color])=CreatePlayer(ColorAndName._2,ColorAndName._1,Players,Colors) //calls CreatePlayer with the user inputs
          Players=auxiliar._1 //updates players list
          Colors=auxiliar._2 //updates colors ist
        case _ => //if there are 8 players this case is activated
          //ErrorMessage.display("SPACE ERROR","Players list is at maximum capacity i.e. 8")//Shows error message
      }
    })
    //Edit Player Button setup+
    val EditPlayer : Button = new Button("Edit Player")
    EditPlayer.setOnAction(e ->{
      Players match { //matches weather there are any players to be edited
        case nil => //if there are no players this case is activated
          ErrorMessage.display("SPACE ERROR", "Players list empty")
        case _ => //if there are players to edit this case is activated
          val layout: VBox = new VBox(10)

          @tailrec
          def listAvailablePlayers(Players: List[Player]): Unit = { //prints all the available Players
            Players.size match { //matches the number of Players in the list to the cases ensuring recursion
              case _ > 0 => // if there are still Players in the List this case gets activated
                val label: Label = new Label()
                label.setText(Players.head._1)
                layout.getChildren().add(label)
                listAvailablePlayers(Players.tail) //recursive call
              case _ => //if there are no more players on the list this case gets activated
                println("Type Player Name") //prompts the user for a player's name
            }
            listAvailablePlayers(Players) //calls printAvailablePlayers
            val OldAndNewName: (String, String) = EditPlayer.status(layout)
            val aux =EditPlayer(OldAndNewName._1, OldAndNewName._2, Players)
            Players=aux
          }
      }
    })
    //StartGame Button setup
    val StartGame : Button = new Button("Start Game")
    //Add Question Button setup
    val AddQuestion : Button = new Button("Add Question")
    //Delete Question Button setup
    val DeleteQuestion : Button = new Button("Delete Question")

    //creating a grid pane with all the buttons
    val mainMenuButtons: GridPane = new GridPane()
    mainMenuButtons.add(CreatePlayer,2,0,1,1)
    mainMenuButtons.add(EditPlayer,2,1,1,1)
    mainMenuButtons.add(StartGame,2,2,1,1)
    mainMenuButtons.add(AddQuestion,2,3,1,1)
    mainMenuButtons.add(DeleteQuestion,2,4,1,1)

    //adding the grid pane with the buttons to a Border Pane and centering the grid pane
    BorderPane.setAllignment(mainMenuButtons,Pos.CENTER)
    val root: BorderPane  = new boarderPane(mainMenuButtons)
    root.setPrefixSize(1000,1000)
    //Creating the scene and setting the stage to the scene and showing it
    Scene scene = new Scene(root);
    primaryStage.setScene(scene)
    primaryStage.show()

  }
}
object Menu{
  def main(args: Array[String]): Unit = {
    application.launch(classOf[GUI], args: _*)
  }
}
