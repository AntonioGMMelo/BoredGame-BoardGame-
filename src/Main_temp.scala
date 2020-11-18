object Main_temp {
  def main(args: Array[String]): Unit = {
    val perguntas : List[Pergunta] = List()
    val tema = "Esportes"
    val esporte = Tema(tema, perguntas)
    println("Foi criado o tema " + esporte.tema)
    val pergunta_a: String = "Em que ano o Benfica foi fundado?"
    val alternativas_a : List[(String,Boolean)] = List(("1906", false), ("1902", false),("1904", true), ("1870", false))
    val a : Pergunta = Pergunta(pergunta_a,alternativas_a)
    val pergunta_b: String = "Quem é o atual campeão europeu?"
    val alternativas_b : List[(String,Boolean)] = List(("França", false), ("Portugal", true),("Espanha", false), ("Alemanha", false))
    val b : Pergunta = Pergunta(pergunta_a,alternativas_a)
    val pergunta_c: String = "Quem é o atual campeão da F1?"
    val alternativas_c : List[(String,Boolean)] = List(("Hamilton", true), ("Leclerc", false),("Vettel", false), ("Verstappen", false))
    val c : Pergunta = Pergunta(pergunta_c,alternativas_c)
    esporte.add_Pergunta(a)
    esporte.add_Pergunta(b)
    esporte.add_Pergunta(c)
    println("aaaaa")
    //val pergunta_X : Pergunta = esporte.select_Pergunta()
    val pergunta_X = esporte.perguntas(1)
    println("Pergunta_X:")
    a.print_Pergunta()
    println("Dica pergunta_X: ")
    //    a.tip_Alternativas()
    //    println("a")
  }
}
