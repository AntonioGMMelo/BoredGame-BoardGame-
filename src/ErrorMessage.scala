import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry._
import javafx.scene._
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.VBox
import javafx.stage._

class ErrorMessage {
  def display(title: String, message: String): Unit ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("ErrorMessage.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(title)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)

    //assigning message to a label
    val label: Label= new Label()
    label.setText(message)
    //creating ok button
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        popUp.close()
      }})

    //adding button and message to a layout
    val layout : VBox= new VBox(2)
    layout.getChildren.addAll(label,ok)
    layout.setAlignment(Pos.CENTER)

    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout)
    popUp.setScene(scene)
    popUp.showAndWait()
  }
}