import javafx.scene.shape._
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.layout.{AnchorPane, BorderPane}
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

class Board {
  def display() ={
  val fxmlLoader = new FXMLLoader(getClass.getResource("Board.fxml"))
  val mainViewRoot: Parent = fxmlLoader.load()
  val rect = new Rectangle()
  val popUp: Stage = new Stage()
  //setting up popUp's parameters and modality
  popUp.initModality(Modality.APPLICATION_MODAL)
  popUp.setTitle("Create Player")
  popUp.setMaxWidth(400)
  popUp.setMaxHeight(300)
  val layout = new AnchorPane(rect)
  val scene: Scene= new Scene(layout)
  popUp.setScene(scene)
  popUp.showAndWait()
  }
}
