import javafx.application.*
import javafx.event.*
import javafx.scene.*
import javafx.stage.*
import javafx.geometry.*

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