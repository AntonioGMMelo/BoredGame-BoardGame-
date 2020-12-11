import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry._
import javafx.scene._
import javafx.scene.control.{Button, ChoiceBox, Label}
import javafx.scene.layout.{GridPane, VBox}
import javafx.stage._

import scala.annotation.tailrec

class EditPlayer {
  var NewColor :String=""
  var OldColor :String=""
  def display(layout: List[String],layout2 :List[String]): (String,String) ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("EditPlayer.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creates 2 observableArrayLists from Lit[strings] layout and layout2
    val list= FXCollections.observableArrayList(layout(0))
    val list2=FXCollections.observableArrayList(layout2(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Edit Player")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    // adds all strings from list[string] layout to the observablearraylist list
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
    // adds all strings from list[string] layout2 to the observablearraylist list2
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
    //creates users input fields
    val ab=new Label("Current Color: ")
    val ac= new Label("New Color: ")
    val oldColor = new ChoiceBox(list)
    val newColor = new ChoiceBox(list2)
    //create button and button action
    val Submit : Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //gets users input values
         OldColor=oldColor.getValue
        NewColor=newColor.getValue()
        //closes window
        popUp.close()
      }
    })
    //creates grid pane with label ab and choicebox oldColor
    val grid= new GridPane
    grid.add(ab,2,0,1,1)
    grid.add(oldColor,3,0,1,1)
    //creates grid pane with label ac and choicebox newColor
    val grid2= new GridPane
    grid2.add(ac,2,0,1,1)
    grid2.add(newColor,3,0,1,1)
    //creates layout3 and adds both gridpanes and the button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(grid,grid2,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (OldColor,NewColor)
  }

}
