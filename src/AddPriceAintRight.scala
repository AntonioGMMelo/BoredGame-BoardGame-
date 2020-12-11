import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, TextField}
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}


class AddPriceAintRight {
  var Name:String=""
  var Price :Double=0.0

  def display(): (String,Double) ={
    val fxmlLoader = new FXMLLoader(getClass.getResource("AddPriceAintRight.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Create Player")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)


    val name : TextField = new TextField("Item Name")
    val price = new TextField("0.0")
    //create button and button action
    val ok : Button = new Button("OK")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        Name = name.getCharacters().toString
        val aux=price.getCharacters.toString.split('.')
        val help=aux(0).toInt.toDouble
        val halp =aux(1).toInt.toDouble/100
        Price=help+halp
        popUp.close()
      }
    })

    //adding button and message to a layout
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(name,price,ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    (Name,Price)
  }
}