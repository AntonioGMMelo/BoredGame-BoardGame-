import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox, TextField}
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class AddFeud {
  var Prompt:String=""
  var BestAnswer :String=""
  var MediumAnswer1: String=""
  var MediumAnswer2: String=""
  var AcceptableAnswer1:String=""
  var AcceptableAnswer2:String=""

  def display(): (String,String,String,String,String,String) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("AddFeud.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Add Feud")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    val prompt : TextField = new TextField("Prompt")
    val bestAnswer : TextField = new TextField("Best Answer")
    val mediumAnswer1 : TextField = new TextField("Medium Answer 1")
    val mediumAnswer2 : TextField = new TextField("Medium Answer 2")
    val acceptableAnswer1 : TextField = new TextField("Acceptable Answer 1")
    val acceptableAnswer2 : TextField = new TextField("Acceptable Answer 2")

    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        Prompt= prompt.getCharacters.toString
        BestAnswer= bestAnswer.getCharacters.toString
        MediumAnswer1= mediumAnswer1.getCharacters.toString
        MediumAnswer2= mediumAnswer2.getCharacters.toString
        AcceptableAnswer1= acceptableAnswer1.getCharacters.toString
        AcceptableAnswer2 =acceptableAnswer2.getCharacters.toString
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(prompt,bestAnswer,mediumAnswer1,mediumAnswer2,acceptableAnswer1,acceptableAnswer2,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (Prompt,BestAnswer,MediumAnswer1,MediumAnswer2,AcceptableAnswer1,AcceptableAnswer2)
  }
}
