import javafx.application.*
import javafx.event.*
import javafx.scene.*
import javafx.stage.*
import javafx.geometry.*

class ErrorMessage {
  def display(title: String, message: String): Unit ={
    val popUp: Stage = new Stage()

    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(title)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)

    val label : Lable = new Lable()
    label.setText(message)
    val ok : Button = new Button("OK")
    ok.setOnAction(e -> popUP.close())

    val layout : VBox= new VBox(10)
    layout.getChilderen().addAll(lable,ok)
    layout.setAlignment(Pos.CENTER)

    val scene: Scene= new Scene(layout)
    popUp.setScene(scene)
    popUp.showAndWait()
  }
}