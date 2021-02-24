import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class RollDaDicePopUp {

  var newRoll:Int=0
  var CanReRoll:Boolean=true

  def display(label:String,canReRoll: Boolean,roll:Int): (Int,Boolean) ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("RollDaDicePopUp.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)
  //creates layout3
    val layout3 = new VBox(3)
    //create button and button action
    val ok2: Button = new Button("I'll Take: "+ roll)
    ok2.setOnAction(new EventHandler[ActionEvent] {
      def handle(event: ActionEvent) = {
        newRoll=roll
        CanReRoll=canReRoll
        //closes window
        popUp.close()
      }
    })
    if(canReRoll) { //if player can re roll i.e. canReRoll= true
      //creates button and button action
      val ok: Button = new Button("Re-Roll Dice")
      ok.setOnAction(new EventHandler[ActionEvent] {
        def handle(event: ActionEvent) = {
          //gets new dice roll
           newRoll=player.getSomething(player.dice)
          //updates boolean
          CanReRoll=false
          val whatever = new ErrorMessage().display(label, "You Got: "+ newRoll)
          //closes window
          popUp.close()
        }
      })
      layout3.getChildren.addAll(ok,ok2) //adds both buttons to layout
    }else{
      layout3.getChildren.addAll(ok2)//adds only "I'll take it..." button
    }

    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return Value for this window
    (newRoll,CanReRoll)
  }
}
