import Pergunta.Alternativa

case class Pergunta(pergunta: String, alternativas: List[(String, Boolean)]) {

  def receive_answer(alternativa: Alternativa): Boolean = Pergunta.receive_answer(alternativa, this.alternativas)

//  def print_Pergunta(): Unit = {
//    Pergunta.print_Pergunta(this)
//  }

  def tip_Alternativa(): List[Alternativa] = {
    Pergunta.tip_Alternativa(this.alternativas)
  }
}


object Pergunta {
  type Alternativa = (String, Boolean)

  def receive_answer(alternativa: Alternativa, alternativas: List[Alternativa]): Boolean = {
    alternativas match {
      case Nil => false
      case x :: t => {
        if (x._1 == alternativa._1) {
          if (x._2 == alternativa._2 && x._2 == true)
            true
          else
            false
        }
        else
          receive_answer(alternativa, t)
      }
    }
  }

  def tip_Alternativa(alternativas: List[Alternativa]): List[Alternativa] = {
    alternativas match {
      case x :: t => {
        if (x._2 == true)
          tip_Alternativa(t)
        else
          (alternativas filter (y => y._2 == true)) :+ x
      }
    }
  }

}
