import javafx.application._
import javafx.event._
import javafx.scene._
import javafx.stage._
import javafx.geometry._
import javafx.collections._

class CreatePlayer {
  def display(layout: VBox): (String,String) ={
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Create Player")
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)

    // Create a combo box
    val Colors :ComboBox = new ComboBox(FXCollections.observableArrayList(layout));
    val name : TextField = new TextFIeld("Player Name")
    BoarderPane.setAllignement(Colors,Pos.CENTER)

    val ok : Button = new Button("OK")
    ok.setOnAction(e ->{
      (Colors.getValue(),name.getCharacters())
      popUp.close()
    })

    //adding button and message to a layout
    val layout2 = new BorderPane()
    layout2.getChilderen().addAll(Colors,name)
    layout2.setAlignment(Pos.CENTER)

    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout2)
    popUp.setScene(scene)
    popUp.showAndWait()

  }
}
