import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, Label, TextField}
import javafx.scene.layout.{GridPane, VBox}
import javafx.stage.{Modality, Stage}

class PriceAintRightPopUp {

  var Price :Double=0.0

  def display(label:String,item:String): (Double) ={
   //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("PriceAintRightPopUp.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)
    //creates label with iteme name
    val name = new Label(item)
    //create users input field
    val l= new Label(item +"'s Price: ")
    val price = new TextField("0.0")
    //create button and button action
    val Submit : Button = new Button("Submit")
    Submit.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //reads use's input and converts it to Double
        val aux=price.getCharacters.toString.split('.')
        val help=aux(0).toInt.toDouble
        val halp =aux(1).toInt.toDouble/100
        Price=help+halp
        //closes Window
        popUp.close()
      }
    })
    //creates gridPane with labe l and textfield price
    val grid = new GridPane
    grid.add(l,2,0,1,1)
    grid.add(price,3,0,1,1)
    //creates layout 3 and adds label name gridpane grid and button submit
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(name,grid,Submit)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (Price)
  }
}

