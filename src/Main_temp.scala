import scala.io.AnsiColor


object Main_temp {

  def a(): Unit = {
    val Player1 = ("Ana", (AnsiColor.BLUE, false))
    val Player2 = ("Ronaldo", (AnsiColor.RED, false))
    val Players: List[user.Player] = List(Player1, Player2)
    val Colors: List[user.Color] = List((AnsiColor.BLACK, false), (AnsiColor.WHITE, false), (AnsiColor.BLUE, false), (AnsiColor.CYAN, false), (AnsiColor.RED, false), (AnsiColor.GREEN, false), (AnsiColor.MAGENTA, false), (AnsiColor.YELLOW, false))
    val NewPlayerColor = user.CreatePlayer("Miguel", 7, Players, Colors)
    println("Output do método CreatePlayer(Lista de Player + Lista de Color) : " + NewPlayerColor)
    val players_2 = user.EditPlayerName("Ronaldo", "Miguel", Players)
    println("Output do método EditPlayerName(Lista de Player) : " + players_2)
    val newPlayerColor2 = user.EditPlayerColor(Player1, 3, Players, Colors)
    println("Output do método EditPlayerColor(Lista de Player + Lista de Color) : " + newPlayerColor2)
  }

  def b(): Unit = {
    val BOUNDARY_LEFT: Int = 0; //Where is the center pixel on the left most spaces
    val BOUNDARY_RIGHT: Int = 1000; //where the center pixel on the right most spaces are
    val BOUNDARY_TOP: Int = 0; //Where the center of the top most spaces are
    val BOUNDARY_BOTTOM: Int = 1000 //Where the center of the bottom most spaces are
    val dice = List(1, 2, 3, 4, 5, 6) // a six faced dice
    val wheelItems = List("Price Aint Right Round", "Move Back 1 Space", "Move Back 1 Space", "Move Back 1 Space", "Move Back 2 Spaces", "Move Back 2 Spaces", "All Players Move Back 2 Spaces", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 1 Space", "Move Forward 2 Spaces", "Move Forward 2 Spaces", "All Players Move Forwards 2 Spaces", "Go To Jail", "Move Forward 3 Spaces", "Stay", "Roll The dice", "Roll The Weighted Dice") //Wheel options
    val cards = List("Roll The dice", "Roll The Weighted Dice", "Go To Jail", "Get Out Of Jail Free Card", "50/50", "Skip Question", "Dilate Time") //card options
    val coin = List("Heads", "Tails")//simulates a coin
    type feud = (String, List[String])
    type item = (String, Double)
    val feudEX: feud = ("Top 5 Pets", List("Dog", "Cat", "Rat", "Fish", "Bird"))//feud questions list
    val feudList: List[feud] = List(feudEX)
    val itemEX: item = ("Cheese Cake", 39.99) //priceAintRight question list
    val itemList: List[item] = List(itemEX)
    val posicao = player.move(30, 100, (1000, 1000), player.moveForward)
    println("Move Player Forward 30 Spaces and moving 100 pixels at a time and starting at position 1000,1000 /n NewPosition:" + posicao)
    val posicao2 = player.move(30, 100, (1000, 1000), player.moveBackward)
    println("Move Player Backward 30 Spaces and moving 100 pixels at a time and starting at position 1000,1000 /n NewPosition:" + posicao2)
    val item_Aleatorio1 = player.getSomething(feudList)
    println("Random Feud: "+item_Aleatorio1)
    val item_Aleatorio2 = player.getSomething(dice)
    println("Dice Roll: "+item_Aleatorio2)
    val item_Aleatorio3 = player.getSomething(wheelItems)
    println("Random Wheel Spin: "+item_Aleatorio3)
    val item_Aleatorio4 = player.getSomething(cards)
    println("Random Card: "+item_Aleatorio4)
    val item_Aleatorio5 = player.getSomething(itemList)
    println("Random Item For Price Ain't Right Round: "+item_Aleatorio5)
    val item_Aleatorio6 = player.getSomething(coin)
    println("Coin Toss: "+item_Aleatorio6)
    val a1 = player.answerFeud(feudEX, "Dog")
    println("Answering Feud With the TOP answer: "+a1)
    val a2 = player.answerFeud(feudEX, "Cat")
    println("Answering Feud With the MEDIUM answer: "+a2)
    val a3 = player.answerFeud(feudEX, "Rat")
    println("Answering Feud With the MEDIUM answer: "+a3)
    val a4 = player.answerFeud(feudEX, "Fish")
    println("Answering Feud With an ACCEPTABLE answer: "+a4)
    val a5 = player.answerFeud(feudEX, "Bird")
    println("Answering Feud With the ACCEPTABLE answer: "+a5)
    val a6 = player.answerFeud(feudEX, "Alpaca")
    println("Answering Feud With the WRONG answer: "+a6)
    val w_dice = player.weightedDice()
    println("Roll Of The Weighted Dice :"+w_dice)
    val price_right = player.priceAintRight(itemEX, List(5.5, 10.9, 25.8, 30.1, 40.05, 50.2))
    println("Item  To Guess Price Of: "itemEX)
    println("Guesses: "+List(5.5, 10.9, 25.8, 30.1, 40.05, 50.2))
    println("Price is Right Round Winner: "+price_right)
  }

  def c(): Unit = {
    val pergunta_a: String = "Em que ano o Benfica foi fundado?"
    val alternativas_a: List[(String, Boolean)] = List(("1906", false), ("1902", false), ("1904", true), ("1870", false))
    val a: Pergunta = Pergunta(pergunta_a, alternativas_a)
    val pergunta_b: String = "Quem é o atual campeão europeu?"
    val alternativas_b: List[(String, Boolean)] = List(("França", false), ("Portugal", true), ("Espanha", false), ("Alemanha", false))
    val b: Pergunta = Pergunta(pergunta_b, alternativas_b)
    val perguntas: List[Pergunta] = List(a, b)
    val esporte = Tema("Esporte", perguntas)
    val pergunta_c: String = "Quem é o atual campeão da F1?"
    val alternativas_c: List[(String, Boolean)] = List(("Hamilton", true), ("Leclerc", false), ("Vettel", false), ("Verstappen", false))
    val c: Pergunta = Pergunta(pergunta_c, alternativas_c)
    val esporte2 = esporte.add_Pergunta(c)
    val pergunta_randon = esporte2.select_Pergunta()
    println("Pergunta aleatoria: " + pergunta_randon)
    val outra_pergunta = esporte2.other_Pergunta(pergunta_randon)
    println("Pergunta nova: " + outra_pergunta)
    val answer = outra_pergunta.receive_answer(outra_pergunta.alternativas.head)
    println("Resposta: " + answer)
    val esporte3 = esporte2.remove_pergunta(pergunta_randon)
    println(esporte3.perguntas(0))
    println(esporte3.perguntas(1))
    val tip = esporte3.perguntas(0).tip_Alternativa()
    println(tip(0))
    println(tip(1))
  }

  def main(args: Array[String]): Unit = {
    a()
    b()
    c()
  }

}
