import scala.annotation.tailrec
import javafx.application._
import javafx._
import javafx.fxml.FXMLLoader
import javafx.scene._
import javafx.stage._
import javafx.geometry._
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.{BorderPane, GridPane}
import javafx.event.{ActionEvent, EventHandler}

class GUI extends Application{
  type Name = String // Name of the Player
  type Color = (String, Boolean) // Color of the Player
  type Player = (Name, Color) // Player type
  type feud = (String,List[String]) //feud type
  type item = (String,Double) //Item type

  var Players:List[Player]=List()
  var Questions:List[Pergunta]=List()
  var Colors:List[Color]=List(("BLACK", false), ("WHITE", false), ("BLUE", false), ("CYAN", false), ("RED", false), ("GREEN", false), ("MAGENTA", false), ("YELLOW", false))
  var Feuds:List[feud]=List()
  var Items:List[item]=List()
  var theme = Tema("yikes",Questions)

  override def start(primaryStage: Stage): Unit ={
    //titling the stage as Main Menu
    primaryStage.setTitle("Main Menu")
    //loading  fmxl
    val fxmlLoader = new FXMLLoader(getClass.getResource("GUI.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    //creating buttons
    //Create Player Button setup
    val CreatePlayer : Button = new Button("Create Player")
    CreatePlayer.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit ={
        var list : List[String] = List()
        val aux=Players.size
        aux match { //Checks the amount o players already created
          case aux if aux< 8 => //if there are less than 8 players this case gets activated
            @tailrec
            def listAvailableColors(Colors:List[(String,Boolean)]): Unit = { //prints all the available colors by checking the Boolean in the list true if the color has been used and false if it hasn't
              val aux2=Colors.size
               aux2 match { //matches the number of colors in the list to the cases ensuring recursion
                case aux2 if aux2 > 0 => // if there are still colors in the List this case gets activated
                  Colors.head._2 match { //Matches the boolean to see weather the color is still unused
                    case false => //if the color hasn't been used this case gets activated
                      val label: Label = new Label()
                      list =list:+Colors.head._1//the color is added as an option for the user
                      listAvailableColors(Colors.tail) //recursive call
                    case _ => //if the color has been used this case gets activated
                      listAvailableColors(Colors.tail) //recursive call
                  }
                case _ => //if there are no colors left in the list this case is activated
              }
            }
            listAvailableColors(Colors)//calls printAvailableColors
            val ColorAndName: (String,String) = new CreatePlayer().display(list)
            val auxiliar:(List[Player], List[Color])=user.CreatePlayer(ColorAndName._1,ColorAndName._2,Players,Colors) //calls CreatePlayer with the user inputs
            Players=auxiliar._1 //updates players list
            Colors=auxiliar._2 //updates colors ist
          case _ => //if there are 8 players this case is activated
            new ErrorMessage().display("SPACE ERROR","Players list is at maximum capacity i.e. 8")//Shows error message
        }
      }
    })
    //Edit Player Button setup
    val EditPlayer : Button = new Button("Edit Player")
    EditPlayer.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        Players.size match { //matches weather there are any players to be edited
          case 0 => //if there are no players this case is activated
            new ErrorMessage().display("SPACE ERROR", "Players list empty")
          case _ => //if there are players to edit this case is activated
            var list: List[String] = List()

            @tailrec
            def listAvailablePlayers(Players: List[Player]): Unit = { //prints all the available Players
              val halp = Players.size
              halp match { //matches the number of Players in the list to the cases ensuring recursion
                case halp if halp > 0 => // if there are still Players in the List this case gets activated
                  list = list :+ Players.head._1
                  listAvailablePlayers(Players.tail) //recursive call
                case _ => //if there are no more players on the list this case gets activated
              }
            }
            var list2:List[String]=List()
            @tailrec
            def listAvailableColors(Colors:List[(String,Boolean)]): Unit = { //prints all the available colors by checking the Boolean in the list true if the color has been used and false if it hasn't
              val aux2=Colors.size
              aux2 match { //matches the number of colors in the list to the cases ensuring recursion
                case aux2 if aux2 > 0 => // if there are still colors in the List this case gets activated
                  Colors.head._2 match { //Matches the boolean to see weather the color is still unused
                    case false => //if the color hasn't been used this case gets activated
                      val label: Label = new Label()
                      list2 =list2:+Colors.head._1//the color is added as an option for the user
                      listAvailableColors(Colors.tail) //recursive call
                    case _ => //if the color has been used this case gets activated
                      listAvailableColors(Colors.tail) //recursive call
                  }
                case _ => //if there are no colors left in the list this case is activated
              }
            }
            listAvailablePlayers(Players) //calls printAvailablePlayers
            listAvailableColors(Colors)//
            val OldAndNewName: (Boolean, String, String, Boolean, String) = new EditPlayer().display(list,list2)//Sends info to popUp page
            if (OldAndNewName._1) { //tells weather to update name
              val aux = user.EditPlayerName(OldAndNewName._2, OldAndNewName._3, Players)//Updates name if need be
              Players = aux //updates the player list
              if(OldAndNewName._4) { //tells weather to update Color too
                val aux = user.EditPlayerColor(Players(Players.indexWhere(_._1.equals(OldAndNewName._3))),OldAndNewName._5,Players,Colors)//updates color if need be
                Players =aux._1 //updates the player list
                Colors=aux._2 //update the colors list
              }
            }else{
              if(OldAndNewName._4) { //Tells weather to update color
                val aux = user.EditPlayerColor(Players(Players.indexWhere(_._1.equals(OldAndNewName._2))),OldAndNewName._5,Players,Colors)//updates color and not name if need be
                Players =aux._1 //updates the player list
                Colors=aux._2 //updates the colors list
              }
            }
        }
      }
    })
    //StartGame Button setup
    val StartGame : Button = new Button("Start Game")
    StartGame.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        val auxer = Players.size
        auxer match {
          case auxer if auxer>1 && auxer<9 =>
            val whatever = new Board ().display()
          case _ =>
            new ErrorMessage().display("SPACE ERROR", "Not Enough Players to Start Game , Min=2")
        }
      }})
    //Add Question Button setup
    val AddQuestion : Button = new Button("Add Question")
      AddQuestion.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        val newQuestion:(String,String,String,String,String,Int) = new AddQuestion().display()
        val list2 =List((newQuestion._2,false),(newQuestion._3,false),(newQuestion._4,false),(newQuestion._5,false))
        val list = list2.updated(newQuestion._6-1,(list2(newQuestion._6-1)._1,true))
        val p:Pergunta=Pergunta(newQuestion._1,list)
        val newQuestionsList = Tema.add_Pergunta(p,theme)
        Questions= newQuestionsList.perguntas
        theme= newQuestionsList
      }})

    //Delete Question Button setup
    val DeleteQuestion : Button = new Button("Delete Question")
    DeleteQuestion.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        Questions.size match { //matches weather there are any players to be edited
          case 0 => //if there are no players this case is activated
            new ErrorMessage().display("SPACE ERROR", "Question list empty")
          case _ => //if there are players to edit this case is activated
            var list: List[String] = List()
            @tailrec
            def listAvailableQuestions(Questions: List[Pergunta]): Unit = { //prints all the available Players
              val halp = Questions.size
              halp match { //matches the number of Players in the list to the cases ensuring recursion
                case halp if halp > 0 => // if there are still Players in the List this case gets activated
                  list = list :+ Questions.head.pergunta
                  listAvailableQuestions(Questions.tail) //recursive call
                case _ => //if there are no more players on the list this case gets activated
              }
            }
            listAvailableQuestions(Questions)
            val QuestionToDelete: String = new DeleteQuestion().display(list)//Sends info to popUp page
            val newQuestionList = Tema.remove_pergunta(Questions(Questions.indexWhere(_.pergunta.equals(QuestionToDelete))),theme).perguntas
            Questions=newQuestionList
            theme=Tema(theme.tema,Questions)
        }
      }})
    val AddFeud : Button = new Button("Add Feud")
      AddFeud.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        val newFeud:(String,String,String,String,String,String) = new AddFeud().display()
        val list =List(newFeud._2,newFeud._3,newFeud._4,newFeud._5,newFeud._6)
        val feudToAdd :feud = new feud(newFeud._1,list)
        val newFeudsList=user.AddFeud(feudToAdd,Feuds)
        Feuds=newFeudsList
      }})
    val DeleteFeud : Button = new Button("Delete Feud")
      DeleteFeud.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        Feuds.size match { //matches weather there are any players to be edited
          case 0 => //if there are no players this case is activated
            new ErrorMessage().display("SPACE ERROR", "Feud list empty")
          case _ => //if there are players to edit this case is activated
            var list: List[String] = List()
            @tailrec
            def listAvailableFeuds(Feuds: List[feud]): Unit = { //prints all the available Players
              val halp = Feuds.size
              halp match { //matches the number of Players in the list to the cases ensuring recursion
                case halp if halp > 0 => // if there are still Players in the List this case gets activated
                  list = list :+ Feuds.head._1
                  listAvailableFeuds(Feuds.tail) //recursive call
                case _ => //if there are no more players on the list this case gets activated
              }
            }
            listAvailableFeuds(Feuds)
            val FeudToDelete: String = new DeleteFeud().display(list)//Sends info to popUp page
            val feudToDelete=Feuds(Feuds.indexWhere(_._1.equals(FeudToDelete)))
            val newFeudList = user.DeleteFeud(feudToDelete,Feuds)
            Feuds=newFeudList
        }
      }})
    val AddPriceAintRight : Button = new Button("Add Price Ain't Right Item")
    AddPriceAintRight.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        val newItem:(String,Double) = new AddPriceAintRight().display()
        newItem._2 match {
          case 0.0=>
            new ErrorMessage().display("INPUT ERROR", "Price Unacceptable please add a price like so: dolars.cents i.e. 111.11")
          case _ =>
            val itemToAdd= new item(newItem._1,newItem._2)
          val newItemsList=user.AddItem(itemToAdd,Items)
          Items=newItemsList
        }
      }})
    val DeletePriceAintRight : Button = new Button("Delete Price Ain't Right Item")
    DeletePriceAintRight.setOnAction(new EventHandler[ActionEvent]{
      def handle(actionEvent: ActionEvent): Unit = {
        Items.size match { //matches weather there are any players to be edited
          case 0 => //if there are no players this case is activated
            new ErrorMessage().display("SPACE ERROR", "Items list empty")
          case _ => //if there are players to edit this case is activated
            var list: List[String] = List()
            @tailrec
            def listAvailableItems(Items: List[item]): Unit = { //prints all the available Players
              val halp = Items.size
              halp match { //matches the number of Players in the list to the cases ensuring recursion
                case halp if halp > 0 => // if there are still Players in the List this case gets activated
                  list = list :+ Items.head._1
                  listAvailableItems(Items.tail) //recursive call
                case _ => //if there are no more players on the list this case gets activated
              }
            }
            listAvailableItems(Items)
          val ItemToDelete: String = new DeleteItem().display(list)//Sends info to popUp page
          val itemAux=Items(Items.indexWhere(_._1.equals(ItemToDelete)))
          val newItemList = user.DeleteItem(itemAux,Items)
          Items=newItemList
        }
      }})
    //creating a grid pane with all the buttons
    val mainMenuButtons: GridPane = new GridPane()
    mainMenuButtons.add(CreatePlayer,2,0,1,1)
    mainMenuButtons.add(EditPlayer,2,1,1,1)
    mainMenuButtons.add(StartGame,2,2,1,1)
    mainMenuButtons.add(AddQuestion,2,3,1,1)
    mainMenuButtons.add(DeleteQuestion,2,4,1,1)
    mainMenuButtons.add(AddFeud,2,5,1,1)
    mainMenuButtons.add(DeleteFeud,2,6,1,1)
    mainMenuButtons.add(AddPriceAintRight,2,7,1,1)
    mainMenuButtons.add(DeletePriceAintRight,2,8,1,1)
    mainMenuButtons.setAlignment(Pos.CENTER)

    //adding the grid pane with the buttons to a Border Pane and centering the grid pane
    BorderPane.setAlignment(mainMenuButtons,Pos.CENTER)
    val root: BorderPane  = new BorderPane(mainMenuButtons)
    root.setPrefSize(1000,1000)
    //Creating the scene and setting the stage to the scene and showing it
    val scene:Scene = new Scene(root);
    primaryStage.setScene(scene)
    primaryStage.show()

  }
}
object Menu{
  def main(args: Array[String]): Unit = {
    application.Application.launch(classOf[GUI], args: _*)
  }
}
