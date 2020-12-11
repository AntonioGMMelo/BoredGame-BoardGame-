import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
import javafx.scene.layout.{GridPane, VBox}
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class AddQuestion {

  var Question:String=""
  var Option1:String=""
  var Option2:String=""
  var Option3:String=""
  var Option4:String=""
  var Correct:Int=0

  def display(): (String,String,String,String,String,Int) ={
    //laoding fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("AddQuestion.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //creating integer list from 1 to 4 for coicebox
    val list = FXCollections.observableArrayList(1)
    list.add(2)
    list.add(3)
    list.add(4)
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Add Question")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)
    //creating user input fields
    val question = new TextField("Question")
    val option1 = new TextField("Option 1")
    val option2 = new TextField("Option 2")
    val option3 = new TextField("Option 3")
    val option4 = new TextField("Option 4")
    val label = new Label("Correct Option: ")
    val correct= new ChoiceBox(list)
    correct.setValue(1)
    //create button and button action
    val Submit: Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //gets user's Inputs
        Question=question.getCharacters.toString
        Option1=option1.getCharacters.toString
        Option2=option2.getCharacters.toString
        Option3=option3.getCharacters.toString
        Option4=option4.getCharacters.toString
        Correct=correct.getValue
        //Closes The Window
        popUp.close()
      }
    })
    //adding label and correct to grid pane
    val pain = new GridPane
    pain.add(label,2,0,1,1)
    pain.add(correct,3,0,1,1)

    //creating layout3 and adding Textfields and grid pane and button to layout3
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(question,option1,option2,option3,option4,pain,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //Return Vaue for this window
    (Question,Option1,Option2,Option3,Option4,Correct)
  }
}
