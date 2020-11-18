//import javafx.application.*
//import javafx.event.*
//import javafx.scene.*
//import javafx.stage.*
//import javafx.geometry.*
//
//class ErrorMessage {
//  def display(title: String, message: String): Unit ={
//    //creating stage
//    val popUp: Stage = new Stage()
//    //setting up popUp's parameters and modality
//    popUp.initModality(Modality.APPLICATION_MODAL)
//    popUp.setTitle(title)
//    popUp.setMinWidth(400)
//    popUp.setMinHeight(300)
//
//    //assigning message to a label
//    val label : Lable = new Lable()
//    label.setText(message)
//    //creating ok button
//    val ok : Button = new Button("OK")
//    ok.setOnAction(e -> popUP.close())
//
//    //adding button and message to a layout
//    val layout : VBox= new VBox(2)
//    layout.getChilderen().addAll(label,ok)
//    layout.setAlignment(Pos.CENTER)
//
//    //creating scene and setting stage to scene
//    val scene: Scene= new Scene(layout)
//    popUp.setScene(scene)
//    popUp.showAndWait()
//  }
//}