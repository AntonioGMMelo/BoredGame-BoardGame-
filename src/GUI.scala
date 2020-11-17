import javafx.application.Application
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class MainMenu extends Application{
  @Override
  def start(primaryStage: Stage): Unit throws Exception={

    primaryStage.setTittle("Main Menu")

    val fxmlLoader = new FXMLLoader(getClass.getResource("controller.fxml"))

    val mainViewRoot: Parent = fxmlLoader.load()

    val CreatePlayer : Button = new Button("Create Player")
    val EditPlayer : Button = new Button("Edit Player")
    val StartGame : Button = new Button("Start Game")
    val AddQuestion : Button = new Button("Add Question")
    val DeleteQuestion : Button = new Button("Delete Question")

    val mainMenuButtons: GridPane = new GridPane()
    mainMenuButtons.add(CreatePlayer,2,0,1,1)
    mainMenuButtons.add(EditPlayer,2,1,1,1)
    mainMenuButtons.add(StartGame,2,2,1,1)
    mainMenuButtons.add(AddQuestion,2,3,1,1)
    mainMenuButtons.add(DeleteQuestion,2,4,1,1)

    BorderPane.setAllignment(mainMenuButtons,Pos.CENTER)
    val root: BorderPane  = new BoarderPane(mainMenuButtons)
    root.setPrefixSize(500,500)

    Scene scene = new Scene(root);
    primaryStage.setScene(scene)
    primaryStage.show()

    object mainMenu {
      def main(args: Array[String]): Unit = {
        Application.launch(classOf[MainMenu], args: _*)
      }
    }
  }