object Main_temp {
  def main(args: Array[String]): Unit = {
    val pergunta_a: String = "Em que ano o Benfica foi fundado?"
    val alternativas_a : List[(String,Boolean)] = List(("1906", false), ("1902", false),("1904", true), ("1870", false))
    val a : Pergunta = Pergunta(pergunta_a,alternativas_a)
    val pergunta_b: String = "Quem é o atual campeão europeu?"
    val alternativas_b : List[(String,Boolean)] = List(("França", false), ("Portugal", true),("Espanha", false), ("Alemanha", false))
    val b : Pergunta = Pergunta(pergunta_b,alternativas_b)
    val perguntas: List[Pergunta] = List(a,b)
    val esporte = Tema("Esporte",perguntas)
    val pergunta_c: String = "Quem é o atual campeão da F1?"
    val alternativas_c : List[(String,Boolean)] = List(("Hamilton", true), ("Leclerc", false),("Vettel", false), ("Verstappen", false))
    val c : Pergunta = Pergunta(pergunta_c,alternativas_c)
    val esporte2 = esporte.add_Pergunta(c)
    val pergunta_randon = esporte2.select_Pergunta()
    println(pergunta_randon.pergunta)
    println(pergunta_randon.alternativas.head)
    val answer = pergunta_randon.receive_answer(pergunta_randon.alternativas.head)
    println(answer)
    val esporte3 = esporte2.remove_pergunta(pergunta_randon)
    println(esporte3.perguntas(0))
    println(esporte3.perguntas(1))
    val tip = esporte3.perguntas(0).tip_Alternativa()
    println(tip(0))
    println(tip(1))
    }

}
