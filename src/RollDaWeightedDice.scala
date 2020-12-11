import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class RollDaWeightedDice {
  var roll:Int=0

  def display(label:String): Int ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("RollDaWeightedDice.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    //create button and button action
    val ok : Button = new Button("Spin Wheel")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        val r=player.weightedDice()
        val spin2 =new  ErrorMessage().display(label,r.toString)
        roll=r
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (roll)
  }
}
