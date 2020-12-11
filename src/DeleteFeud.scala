import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.control.{Button, ChoiceBox, Label}
import javafx.scene.layout.{GridPane, VBox}
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class DeleteFeud {
  var Feud:String=""

  def display(layout: List[String]): String ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeleteFeud.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creates observablearraylist list of of list[Strings]layout
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Delete Feud")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    //adds each string of the list[string] to the observablearraylist list
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
    //creates user input fields
    val l= new Label("Feud to Delete: ")
    val feuds = new ChoiceBox(list)
    //create button and button action
    val Submit : Button = new Button("OK")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //get choice form choicebox
        Feud =feuds.getValue()
        //closes the window
        popUp.close()
      }
    })
    //create grid pane with labe l and choicebox feuds
    val g= new GridPane()
    g.add(l,2,0,1,1)
    g.add(feuds,3,0,1,1)
    //creating layout3 and adding gridpane l and button submit
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(g,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for window
    (Feud)
  }
}
