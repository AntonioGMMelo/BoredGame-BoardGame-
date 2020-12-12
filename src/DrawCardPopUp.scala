import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry.Pos
import javafx.scene.{Parent, Scene}
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.{Modality, Stage}

class DrawCardPopUp {

  var Card:String=""
  var CanReDraw:Boolean=true

  def display(label:String,canReDraw: Boolean,card:String): (String,Boolean) = {
    //load fxml
    val fxmlLoader = new FXMLLoader(getClass.getResource("DrawCardPopUp.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating stage
    val popUp: Stage = new Stage()
    //setting up popUp's parameters and modality
    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMaxWidth(400)
    popUp.setMaxHeight(300)
    //creates vbox
    val layout3 = new VBox(3)
    //create button and button action "Dont RE-Draw" i.e I'll take: card(last card draw)
    val ok2: Button = new Button("I'll Take: " + card)
    ok2.setOnAction(new EventHandler[ActionEvent] {
      def handle(event: ActionEvent) = {
        Card=card
        CanReDraw=canReDraw
        popUp.close()
      }
    })
    //if user can re draw ie canredraw=true
    if (canReDraw) {
      val ok: Button = new Button("Re-Draw Card")
      ok.setOnAction(new EventHandler[ActionEvent] {
        def handle(event: ActionEvent) = {
          //draws new card
          Card = player.getSomething(player.cards)
          //sets boolean to false
          CanReDraw = false
          //closes Window
          popUp.close()
        }
      })
      //adds both buttons to layout
      layout3.getChildren.addAll(ok, ok2)
    } else {
      //adds only "Dont redraw" button
      layout3.getChildren.addAll(ok2)
    }

    layout3.setAlignment(Pos.CENTER)
    //creating scene and setting stage to scene
    val scene: Scene = new Scene(layout3)
    popUp.setScene(scene)
    popUp.showAndWait()
    //returns the oldDraw/newDraw and the boolean
    (Card, CanReDraw)
  }
}
