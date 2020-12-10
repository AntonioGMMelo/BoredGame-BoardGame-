
import java.awt.ScrollPane

import javafx.fxml.{FXML, FXMLLoader}
import javafx.geometry.Orientation
import javafx.scene.control.{Button, ScrollBar}
import javafx.scene.layout.AnchorPane
import javafx.scene.shape._
import javafx.scene.{Parent, Scene}
import javafx.stage.{Modality, Stage}
import javafx.scene.layout.VBox

class Board {
  type PlayersExtra=(String,(Int,Int),Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean,Boolean)
  @FXML
  var circle1 :Circle =_
  var circle2 :Circle =_
  var circle3 :Circle =_
  var circle4 :Circle =_
  var circle5 :Circle =_
  var circle6 :Circle =_
  var circle7 :Circle =_
  var circle8 :Circle =_

  def display(players:List[PlayersExtra]) = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("Board.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    //    val rect = new Rectangle()
    val popUp: Stage = new Stage()
    var circles: List[Circle]= List(circle1,circle2,circle3,circle4,circle5,circle6,circle7,circle8)

  for(i<-0 to players.size-1){
    if(!(players(i).equals("NO"))) {
      if (players(i)._1.equals("BLACK")) {
        circles(i).setFill(javafx.scene.paint.Color.BLACK)
      } else {
        if (players(i)._1.equals("WHITE")) {
          circles(i).setFill(javafx.scene.paint.Color.WHITE)
        } else {
          if (players(i)._1.equals("BLUE")) {
            circles(i).setFill(javafx.scene.paint.Color.BLUE)
          } else {
            if (players(i)._1.equals("CYAN")) {
              circles(i).setFill(javafx.scene.paint.Color.CYAN)
            } else {
              if (players(i)._1.equals("RED")) {
                circles(i).setFill(javafx.scene.paint.Color.RED)
              } else {
                if (players(i)._1.equals("GREEN")) {
                  circles(i).setFill(javafx.scene.paint.Color.GREEN)
                } else {
                  if (players(i)._1.equals("MAGENTA")) {
                    circles(i).setFill(javafx.scene.paint.Color.MAGENTA)
                  } else {
                    if (players(i)._1.equals("YELLOW")) {
                      circles(i).setFill(javafx.scene.paint.Color.YELLOW)
                    }
                  }
                }

              }
            }
          }
        }
      }
      circles(i).setVisible(true)
    }
  }

      val scene: Scene= new Scene(mainViewRoot)
      popUp.setScene(scene)
      popUp.showAndWait()
  }
}
