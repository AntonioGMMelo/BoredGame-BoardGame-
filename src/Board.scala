
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
  @FXML
  var a:Button=_
  def display(players:List[PlayersExtra]) = {
    val fxmlLoader = new FXMLLoader(getClass.getResource("Board.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()

    //    val rect = new Rectangle()
    val popUp: Stage = new Stage()
//    val circleex = new Circle()
//    var circles: List[Circle]= List(circleex,circleex,circleex,circleex,circleex,circleex,circleex,circleex)
//    val layout = new VBox(2)
//    val layout2 = new AnchorPane()
//    layout.setPrefHeight(1100)
//    layout.setPrefWidth(1100)
//    println(players)
//  for(i<-0 to players.size-1){
//    if(!(players(i).equals("NO"))) {
//      val player = new Circle(players(i)._2._1, players(i)._2._2,10 - i)
//      circles = circles.updated(i, player)
//      if (players(i)._1.equals("BLACK")) {
//        player.setFill(javafx.scene.paint.Color.BLACK)
//      } else {
//        if (players(i)._1.equals("WHITE")) {
//          player.setFill(javafx.scene.paint.Color.WHITE)
//        } else {
//          if (players(i)._1.equals("BLUE")) {
//            player.setFill(javafx.scene.paint.Color.BLUE)
//          } else {
//            if (players(i)._1.equals("CYAN")) {
//              player.setFill(javafx.scene.paint.Color.CYAN)
//            } else {
//              if (players(i)._1.equals("RED")) {
//                player.setFill(javafx.scene.paint.Color.RED)
//              } else {
//                if (players(i)._1.equals("GREEN")) {
//                  player.setFill(javafx.scene.paint.Color.GREEN)
//                } else {
//                  if (players(i)._1.equals("MAGENTA")) {
//                    player.setFill(javafx.scene.paint.Color.MAGENTA)
//                  } else {
//                    if (players(i)._1.equals("YELLOW")) {
//                      player.setFill(javafx.scene.paint.Color.YELLOW)
//                    }
//                  }
//                }
//
//              }
//            }
//          }
//        }
//      }
//      layout.getChildren.add(circles(i))
//    }
//  }
//  //setting up popUp's parameters and modality
//    popUp.initModality(Modality.APPLICATION_MODAL)
//    popUp.setTitle("Create Player")
//    val s = new ScrollPa()
//    s.setMin(0)
//    s.setMax(1100)
//    s.setValue(0)
//    s.setOrientation(Orientation.VERTICAL)
//    s.setUnitIncrement(200)
//    s.setBlockIncrement(10)
////    layout2.getChildren.addAll(s,layout)
      val scene: Scene= new Scene(mainViewRoot)
      popUp.setScene(scene)
      popUp.showAndWait()
  }
}
