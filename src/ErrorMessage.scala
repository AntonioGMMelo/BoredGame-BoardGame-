//import javafx.application.Application
//import javafx.event.{ActionEvent, EventHandler}
//import javafx.scene._
//import javafx.stage._
//import javafx.geometry._
//import javafx.scene.control.{Button, ComboBox, Label, TextField}
//import javafx.scene.layout.VBox
//
//class ErrorMessage extends Application {
//  override def start(popUp:Stage/*title: String, message: String*/): Unit ={
//      val title2="AAAAAAAAAAAAAAAAA"
//      val message2="BBBBBBBBBBBBBBBBBBBBBBBBBBBBB"
//    //creating stage
//    //val popUp: Stage = new Stage()
//    //setting up popUp's parameters and modality
//    popUp.initModality(Modality.APPLICATION_MODAL)
//    popUp.setTitle(title2)
//    popUp.setMinWidth(400)
//    popUp.setMinHeight(300)
//
//    //assigning message to a label
//    val label: Label= new Label()
//    label.setText(message2)
//    //creating ok button
//    val ok : Button = new Button("OK")
//    ok.setOnAction(new EventHandler[ActionEvent]{
//      def handle(event:ActionEvent)  = {
//        popUp.close()
//      }})
//
//    //adding button and message to a layout
//    val layout : VBox= new VBox(2)
//    layout.getChildren.addAll(label,ok)
//    layout.setAlignment(Pos.CENTER)
//
//    //creating scene and setting stage to scene
//    val scene: Scene= new Scene(layout)
//    popUp.setScene(scene)
//    popUp.showAndWait()
//  }
//  object FxApp {
//    def main(args: Array[String]): Unit = {
//      Application.launch(classOf[ErrorMessage], args: _*)
//    }
//  }
//}