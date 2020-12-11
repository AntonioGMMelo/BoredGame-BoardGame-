import javafx.collections.FXCollections
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ChoiceBox, Label, TextField}
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class Feud {
  type feud = (String,List[String]) //feud type
  var Spaces :Int=0

  def display(label:String,layout: List[feud]): (Int) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("Feud.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val list= FXCollections.observableArrayList(layout(0))
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)
    val a= player.getSomething(layout)
    val label2= new Label(a._1)
    val answer = new TextField("Answer Here")
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        Spaces=player.answerFeud(a,answer.getText())
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(label2,answer,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    Spaces
  }
}
