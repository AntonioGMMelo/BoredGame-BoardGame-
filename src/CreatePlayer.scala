import javafx.collections.{FXCollections, ObservableArray}
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry._
import javafx.scene._
import javafx.scene.control.{Button, ChoiceBox, TextField}
import javafx.scene.layout.VBox
import javafx.stage._
import scala.annotation.tailrec


class CreatePlayer {
  var Color:String=""

  def display(layout: List[String]): (String) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("CreatePlayer.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Create Player")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    // Create a combo box
    @tailrec
    def whatever(i:Int){
      val help = layout.size
      i match {
        case i if i==help =>
          println()
        case i if i<help=>
          list.add(layout(i))
          whatever(i+1)
      }
    }
    whatever(1)
    val Colors = new ChoiceBox(list)
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
     def handle(event:ActionEvent)  = {
       Color =Colors.getValue().toString
       popUp.close()
     }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(Colors,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (Color)
  }
}