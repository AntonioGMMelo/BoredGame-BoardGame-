import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox}
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class SpinDaWheel {

  var spin:String=""
  var CanReSpin:Boolean=true

  def display(label:String,canReSpin: Boolean): (String,Boolean) ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("SpinDaWheel.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)

    //create button and button action
    val ok : Button = new Button("Spin Wheel")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        val spin2 = new SpinDaWheelPopUp().display(label,canReSpin,player.getSomething(player.wheelItems))//gets values from spinTheWheelPopUp
        spin=spin2._1 //asssings the wheel item gotten
        CanReSpin=spin2._2 //updates boolean
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
    //return value for this window
    (spin,CanReSpin)
  }
}
