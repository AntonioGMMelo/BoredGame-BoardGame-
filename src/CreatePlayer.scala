import javafx.collections.{FXCollections, ObservableArray}
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry._
import javafx.scene.{control, _}
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
import javafx.scene.layout.{GridPane, VBox}
import javafx.stage._

import scala.annotation.tailrec


class CreatePlayer {

  var Color:String=""

  def display(layout: List[String]): (String) ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("CreatePlayer.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //create observableArrayList list from List[String]
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Create Player")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    // Adding each string of the list[String] to the observableArrayList
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
    //Creating User Input Fields
    val labl =  new Label("Pick a Color: ")
    val Colors = new ChoiceBox(list)
    //create button and button action
    val Submit : Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
     def handle(event:ActionEvent)  = {
       //getting choice from choiceBox
       Color =Colors.getValue().toString
       //closing the window
       popUp.close()
     }
    })
    //creating grid pane with label and color combobox
    val a= new GridPane()
    a.add(labl,2,0,1,1)
    a.add(Colors,3,0,1,1)
    //creating layout3 and adding gridpane and submit button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(a,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (Color)
  }
}