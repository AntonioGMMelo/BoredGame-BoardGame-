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
  var EditName:Boolean=false
  var OldName:String=""
  var NewName :String=""
  var EditColor:Boolean=false
  var NewColor :String=""
  def display(layout: List[String],layout2 :List[String]): (Boolean,String,String,Boolean,String) ={
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
    val editName = new CheckBox("Edit Name")
    val oldName = new ChoiceBox(list)
    val newName : TextField = new TextField("Player Name")
    val editColor = new CheckBox("Edit Color")
    val newColor = new ChoiceBox(list2)
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        EditName= editName.isSelected
        OldName =oldName.getValue().toString
        NewName = newName.getCharacters().toString
        EditColor= editColor.isSelected
        NewColor=newColor.getValue()
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(editName,oldName,newName,editColor,newColor,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (EditName,OldName,NewName,EditColor,NewColor)
  }

}
