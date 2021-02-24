import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}

import scala.annotation.tailrec

class PriceAintRight {

  type item = (String,Double) //Item type
  type PlayerExtra=(String,(Int,Int),Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean)//player type

  var Winner:Int=0

  def display(layout: List[PlayerExtra],list:List[item]): Int ={
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("PriceAintRight.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle("PriceAintRight")
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)
    val item2 = player.getSomething(list)
    var answers:List[Double]=List()
    // Create a combo box
    @tailrec
    def whatever(i:Int){
      val help = layout.size
      i match {
        case i if i==help =>
          println()
        case i if i<help=>
        val d:Double=new PriceAintRightPopUp().display(layout(i)._1,item2._1)//gets each players answer
          answers=answers:+d //adds each players answer to a list
          whatever(i+1)
      }
    }
    whatever(0)
    //create button and button action
    val ok : Button = new Button("Submit")
    ok.setOnAction(new EventHandler[ActionEvent]{
      def handle(event:ActionEvent)  = {
        //gets winner of of players answers
        Winner=player.priceAintRight(item2,answers)
        //closes window
        popUp.close()
      }
    })

    //creating layout3 and adding button
    val layout3 = new VBox(2)
    layout3.getChildren.addAll(ok)
    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene= new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //return value for this window
    (Winner)
  }
}
