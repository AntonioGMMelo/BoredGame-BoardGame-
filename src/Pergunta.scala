import Pergunta.Alternativa

case class Pergunta(pergunta: String, alternativas: List[(String, Boolean)]) {

  def receive_answer(alternativa: Alternativa): Boolean = Pergunta.receive_answer(alternativa, this.alternativas)


  def tip_Alternativa(): List[Alternativa] = {
    Pergunta.tip_Alternativa(this.alternativas)
  }

  def alternativas_string(): List[String] = {
    Pergunta.alternativas_string(this.alternativas)
  }

  def alternativas_tip_string(): List[String] = {
    Pergunta.alternativas_string(this.tip_Alternativa())
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

  def alternativas_string(alternativas: List[Alternativa]): List[String] = {
    alternativas match {
      case Nil => Nil
      case x :: t => x._1 :: alternativas_string(t)
    }
  }

}
