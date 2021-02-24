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
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("RollDaDice.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.setTitle(label)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)

    //create button and button action
    val ok : Button = new Button("Roll The Dice")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        val spin2 = new RollDaDicePopUp().display(label,canReRoll,player.getSomething(player.dice))//gets the dice roll and the canReRolll boolean from popUp
        roll=spin2._1 //assigns dice roll value
        CanReRoll=spin2._2 //updates boolean
        //closes window
        popUp.close()
      }
    })

    //creates layout3 and adds button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (roll,CanReRoll)
  }
}
