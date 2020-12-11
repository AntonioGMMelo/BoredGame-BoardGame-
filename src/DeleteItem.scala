import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.control.{Button, ChoiceBox, Label}
import javafx.scene.layout.{GridPane, VBox}
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class DeleteItem {

  var Item:String=""

  def display(layout: List[String]): String ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeleteItem.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating observablearraylist list from List[String]
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Delete Item")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    // adding each string from list[string] to observablearraylist list
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
    //creating user input fields
    val labl= new Label("Item to Delete: ")
    val items = new ChoiceBox(list)
    //create button and button action
    val Submit : Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //getting choice from choice box
       Item=items.getValue
        //closing window
        popUp.close()
      }
    })
    //creating grifpane with label and choicebox
      val g = new GridPane
      g.add(labl,2,0,1,1)
      g.add(items,3,0,1,1)
    //creating layout3 and adding choicebox and button to it
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(g,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (Item)
  }
}
