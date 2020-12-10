import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox, TextField}
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class DeleteItem {
  var Item:String=""

  def display(layout: List[String]): String ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeleteItem.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Delete Item")
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
    val items = new ChoiceBox(list)
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
       Item=items.getValue
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(items,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (Item)
  }
}
