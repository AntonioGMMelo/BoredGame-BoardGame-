import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.control.{Button, TextField}
import javafx.scene.layout.VBox
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

class WinnerQuote {
  var Quote:String=""

  def display(label:String): String ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("WinnerQuote.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)
    //creates user input fields
    val quote:TextField= new TextField("Insert Quote Here")
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        Quote=quote.getText
        popUp.close()
      }
    })

    //create layout3 and add quote and ok button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(quote,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (Quote)
  }
}
