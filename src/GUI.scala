import scala.annotation.tailrec

class MainMenu extends Application{
  @Override
  def start(primaryStage: Stage): Unit throws Exception={
    //titling the stage as Main Menu
    primaryStage.setTittle("Main Menu")
    //loading  fmxl
    val fxmlLoader = new FXMLLoader(getClass.getResource("controller.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating buttons
    //Create Player Button setup
    val CreatePlayer : Button = new Button("Create Player")
    CreatePlayer.setOnAction(e ->{
      val layout : VBox = new VBox(10)
      user.Players.size match { //Checks the amount o players already created
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
                println("Choose Color") //prompts the user for a name and a color
            }
          }
          listAvailableColors(user.Colors)//calls printAvailableColors
          CreatePlayer.display()
          lazy val name =  //Reads name input from user
          val color =  //reads color input from user
          user.CreatePlayer(name,color) //calls CreatePlayer with the user inputs
        case _ => //if there are 8 players this case is activated
          ErrorMessage.display("SPACE ERROR","Players list is at maximum capacity i.e. 8")//Shows error message
      }
    })
    //Edit Player Button setup
    val EditPlayer : Button = new Button("Edit Player")
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
    val root: BorderPane  = new BoarderPane(mainMenuButtons)
    root.setPrefixSize(1000,1000)
    //Creating the scene and setting the stage to the scene and showing it
    Scene scene = new Scene(root);
    primaryStage.setScene(scene)
    primaryStage.show()

    object mainMenu {
      def main(args: Array[String]): Unit = {
        Application.launch(classOf[MainMenu], args: _*)
      }
    }
  }