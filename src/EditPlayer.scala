import javafx.collections.{FXCollections, ObservableArray}
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry._
import javafx.scene._
import javafx.scene.control.{Button, CheckBox, ChoiceBox, TextField}
import javafx.scene.layout.VBox
import javafx.stage._

import scala.annotation.tailrec

class EditPlayer {
  var NewColor :String=""
  var OldColor :String=""
  def display(layout: List[String],layout2 :List[String]): (String,String) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("EditPlayer.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val list= FXCollections.observableArrayList(layout(0))
    val list2=FXCollections.observableArrayList(layout2(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Edit Player")
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
    @tailrec
    def whatever2(i:Int){
      val help = layout2.size
      i match {
        case i if i==help =>
          println()
        case i if i<help=>
          list2.add(layout2(i))
          whatever2(i+1)
      }
    }
    whatever(1)
    whatever2(1)
    val oldColor = new ChoiceBox(list2)
    val newColor = new ChoiceBox(list2)
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
         OldColor=oldColor.getValue
        NewColor=newColor.getValue()
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(newColor,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (OldColor,NewColor)
  }

}
