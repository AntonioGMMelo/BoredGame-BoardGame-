import java.awt.{Label, TextField}

import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class SpinDaWheelPopUp {
  var spin:String=""
  var CanReSpin:Boolean=true

  def display(label:String,canReSpin: Boolean,wheelitem:String): (String,Boolean) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("SpinDaWheelPopUp.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    val layout3 = new VBox(3)
    //create button and button action
    val l= new TextField()
    l.setText(wheelitem)
    val ok2: Button = new Button("I'll Take: "+l)
    ok2.setOnAction(new EventHandler[ActionEvent] {
      def handle(event: ActionEvent) = {
        popUp.close()
      }
    })
    if(canReSpin) {
      val ok: Button = new Button("Re-Spin Wheel")
      ok.setOnAction(new EventHandler[ActionEvent] {
        def handle(event: ActionEvent) = {
          spin=player.getSomething(player.wheelItems)
          CanReSpin=false
          popUp.close()
        }
      })
      layout3.getChildren.addAll(ok,ok2)
    }else{
      layout3.getChildren.addAll(ok2)
    }

    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (spin,CanReSpin)
  }
}
