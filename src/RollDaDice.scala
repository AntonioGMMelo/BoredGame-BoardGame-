import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class RollDaDice {
  var roll:Int=0
  var CanReRoll:Boolean=true

  def display(label:String,canReRoll: Boolean): (Int,Boolean) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("RollDaDice.fxml"))
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
        val spin2 = new RollDaDicePopUp().display(label,canReRoll,player.getSomething(player.dice))
        roll=spin2._1
        CanReRoll=spin2._2
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
    (roll,CanReRoll)
  }
}
