//import javafx.collections.FXCollections
//import javafx.event.{ActionEvent, EventHandler}
//import javafx.scene._
//import javafx.stage._
//import javafx.geometry._
//import javafx.scene.control.{Button, ComboBox, Label, TextField}
//import javafx.scene.layout.{BorderPane, VBox}
//
//class CreatePlayer {
//  var Color:String=""
//  var Name :String=""
//
//  def display(layout: List[String]): (String,String) ={
//    //creating stage
//    val popUp: Stage = new Stage()
//    //setting up popUp's parameters and modality
//    popUp.initModality(Modality.APPLICATION_MODAL)
//    popUp.setTitle("Create Player")
//    popUp.setMinWidth(400)
//    popUp.setMinHeight(300)
//
//    // Create a combo box
//    val Colors :ComboBox = new ComboBox(layout);
//    val name : TextField = new TextField("Player Name")
//    BorderPane.setAlignment(Colors,Pos.CENTER)
//    //create button and button action
//    val ok : Button = new Button("OK")
//    ok.setOnAction(new EventHandler[ActionEvent]{
//     def handle(event:ActionEvent)  = {
//       Color =Colors.getValue().toString
//       Name = name.getCharacters().toString
//     }
//      popUp.close()
//    })
//
//    //adding button and message to a layout
//    val layout2 = new BorderPane()
//    layout2.getChildren.addAll(Colors,name)
//    //creating scene and setting stage to scene
//    val scene: Scene= new Scene(layout2)
//    popUp.setScene(scene)
//    popUp.showAndWait()
//    (Name,Color)
//  }
//
//}

