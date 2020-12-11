import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class DrawCard {
  var card:String=""
  var CanReDraw:Boolean=true

  def display(label:String,canReDraw: Boolean): (String,Boolean) ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("DrawCard.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    //create button and button action
    val draw : Button = new Button("Draw Card")
    draw.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        val spin2 = new SpinDaWheelPopUp().display(label,canReDraw,player.getSomething(player.cards)) //pops Up options for user
        //gets values from SpinDaWheelPopUP
        card=spin2._1
        CanReDraw=spin2._2
        //closes the window
        popUp.close()
      }
    })

    //creating layout3 and adding button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(draw)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value
    (card,CanReDraw)
  }
}
