import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox, TextField}
import javafx.scene.layout.VBox
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
    val fxmlLoader = new FXMLLoader(getClass.getResource("AddQuestion.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    val list = FXCollections.observableArrayList(1)
    list.add(2)
    list.add(3)
    list.add(4)
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Add Question")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)
    val question = new TextField("Question")
    val option1 = new TextField("Option 1")
    val option2 = new TextField("Option 2")
    val option3 = new TextField("Option 3")
    val option4 = new TextField("Option 4")
    val correct= new ChoiceBox(list)
    correct.setValue(1)
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        Question=question.getCharacters.toString
        Option1=option1.getCharacters.toString
        Option2=option2.getCharacters.toString
        Option3=option3.getCharacters.toString
        Option4=option4.getCharacters.toString
        Correct=correct.getValue
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(question,option1,option2,option3,option4,correct,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (Question,Option1,Option2,Option3,Option4,Correct)
  }
}
