import javafx.collections.{FXCollections, ObservableList}
import javafx.event.{ActionEvent, EventHandler}
import javafx.fxml.FXMLLoader
import javafx.geometry._
import javafx.scene._
import javafx.scene.control.{Button, ChoiceBox, Label}
import javafx.scene.layout.VBox
import javafx.stage._

import scala.annotation.tailrec
import scala.language.postfixOps

class Question {

  type Alternativa = (String, Boolean)
  type Permicoes = (Boolean, Boolean, Boolean)
  type Resposta = (Boolean, Boolean, Boolean, Boolean)


  def display(tema: Tema,label:String, permicoes: Permicoes): Resposta = {
    var per_1 = permicoes._1
    var per_2 = permicoes._2
    var per_3 = permicoes._3
    var answer: Boolean = false
    var pergunta = tema.select_Pergunta()
    val fxmlLoader = new FXMLLoader(getClass.getResource("Question.fxml"))
    //    val mainViewRoot: Parent = fxmlLoader.load()
    val popUp: Stage = new Stage()
    popUp.initModality(Modality.APPLICATION_MODAL)
    //setting up popUp's parameters and modality
    //    popUp.initModality(Modality.APPLICATION_MODAL)
    popUp.setTitle(label)
    popUp.setMinWidth(400)
    popUp.setMinHeight(300)

    var cb = newChoiceBox(pergunta)

    var text = new Label()
    text.setText(pergunta.pergunta)

    val responder_button: Button = new Button("Answer")
    val otheQ_button: Button = new Button("Skip Question")
    val tip_button: Button = new Button("50/50")
    val ganhar_button: Button = new Button("Get It Right")


    responder_button.setOnAction(new EventHandler[ActionEvent] {
      def handle(event: ActionEvent) = {
        val s = cb.getValue()
        val alt = (s, true)
        answer = pergunta.receive_answer(alt)
        popUp.close()
      }
    })

    tip_button.setOnAction(new EventHandler[ActionEvent] {
      def handle(event: ActionEvent) = {
        cb = makeAlternativa(pergunta)
        per_2 = false
        set_layout(popUp, text, cb, responder_button, tip_button, otheQ_button, ganhar_button, (per_1, per_2, per_3))
        popUp.close()
      }
    })

    otheQ_button.setOnAction(new EventHandler[ActionEvent] {
      override def handle(t: ActionEvent): Unit = {
        val p2 = pergunta
        pergunta = tema.other_Pergunta(p2)
        println("Nova pergunta = " + pergunta)
        text.setText(pergunta.pergunta)
        cb = newChoiceBox(pergunta)
        per_1 = false
        set_layout(popUp, text, cb, responder_button, tip_button, otheQ_button, ganhar_button, (per_1, per_2, per_3))
      }
    })

    ganhar_button.setOnAction(new EventHandler[ActionEvent] {
      override def handle(t: ActionEvent): Unit = {
        answer = true
        popUp.close()
      }
    })

    set_layout(popUp, text, cb, responder_button, tip_button, otheQ_button, ganhar_button, permicoes)
    (answer, per_1, per_2, per_3)
  }

  def lista_observar(l: List[String]): ObservableList[String] = {
    val list = FXCollections.observableArrayList(l(0))

    @tailrec
    def whatever(i: Int) {
      val help = l.size
      i match {
        case i if i == help =>
          println()
        case i if i < help =>
          list.add(l(i))
          whatever(i + 1)
      }
    }

    whatever(1)
    list
  }

  def set_layout(popUp: Stage, ta: Label, cb: ChoiceBox[String], b_responder: Button, tip_button: Button, otheQ_button: Button, ganhar_button: Button, p: Permicoes) = {
    val layout = new VBox(2)
    layout.getChildren.addAll(ta, cb, b_responder)
    if (p._1 == true)
      layout.getChildren.add(otheQ_button)
    if (p._2 == true)
      layout.getChildren.add(tip_button)
    if (p._3 == true)
      layout.getChildren.add(ganhar_button)
    layout.setAlignment(Pos.CENTER)
    val scene: Scene = new Scene(layout)
    popUp.setScene(scene)
    popUp.showAndWait()
  }

  def carregou_Dica(tip_button: Button, cb: ChoiceBox[String]): Unit = {
    tip_button.setVisible(false)
  }

  def newChoiceBox(pergunta: Pergunta): ChoiceBox[String] = {
    println("cb = " + pergunta)
    val l = pergunta.alternativas_string()
    val list = lista_observar(l)
    var cb = new ChoiceBox(list)
    cb
  }

  def makeAlternativa(pergunta: Pergunta): ChoiceBox[String] = {
    val l = pergunta.alternativas_tip_string()
    val list = lista_observar(l)
    var cb = new ChoiceBox(list)
    cb
  }
}
