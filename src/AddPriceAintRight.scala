import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, Label, TextField}
import javafx.scene.layout.{GridPane, VBox}
import javafx.stage.{Modality, Stage}


class AddPriceAintRight {
  var ItemName: String=""
  var ItemPrice :Double = 0.0

  def display(): (String,Double) ={
    //loading fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("AddPriceAintRight.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("Add Price Ain't Right")
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)

    //Create Inuts(2 TextFields)
    val name : TextField = new TextField("Item Name")
    val label : Label= new Label("Item Price: ")
    val price = new TextField("0.0")
    //create submit button and button action
    val Submit : Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //Get ItemName From Input
        ItemName = name.getCharacters().toString
        //Get And Convert Double ItemPrice From Sting Input
        val aux=price.getCharacters.toString.split('.')
        val help=aux(0).toInt.toDouble
        val halp =aux(1).toInt.toDouble/100
        ItemPrice=help+halp
        //Close Window
        popUp.close()
      }
    })
    //adding Label to Item Price Field
    val au= new GridPane()
    au.add(label,2,0,1,1)
    au.add(price,3,0,1,1)
    //Adding Item Name And Item Value and Submit Button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(name,au,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //Return For THis Display
    (ItemName,ItemPrice)
  }
}
