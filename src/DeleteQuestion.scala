import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
import javafx.scene.layout.{GridPane, VBox}
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class DeleteQuestion {

  var Question:String=""

  def display(layout: List[String]): String ={
  //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("DeleteQuestion.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //create observablearraylist from List[String]
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Delete Question")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    //Adding each String from List[String] layout to the observableaarraylist
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
    val la= new Label("Question to Delete: ")
    //creating user input fields
    val Questions = new ChoiceBox(list)
    //create button and button action
    val Submit : Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //gets choice from choice box
        Question=Questions.getValue
        //closes window
        popUp.close()
      }
    })
    //creates a gridpane with the label la and the choicebox Questions
    val grid = new GridPane
    grid.add(la,2,0,1,1)
    grid.add(Questions,3,0,1,1)
    //creates layout3 and adds gridpane and button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(grid,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //Return value for this window
    (Question)
  }
}
