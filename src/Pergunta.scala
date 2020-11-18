import Pergunta.Alternativa

case class Pergunta(pergunta: String, alternativas: List[(String, Boolean)]) {

  def receive_answer(alternativa: Alternativa): Boolean = Pergunta.receive_answer(alternativa, this.alternativas)

  def print_Pergunta(): Unit ={Pergunta.print_Pergunta(this)}

  def tip_Alternativas(): Unit = {Pergunta.tip_Alternativa(this)}
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

  def print_Pergunta(pergunta: Pergunta): Unit = {
    println(pergunta.pergunta)

    def print_Alternativas(alternativas: List[(String, Boolean)]): Unit = {
      println(alternativas.head._1)
      alternativas match {
        case x :: t => {
          println(x._1)
          if(t != Nil)
            print_Alternativas(t)
        }
      }
    }
    print_Alternativas(pergunta.alternativas)
  }

  def tip_Alternativa(pergunta: Pergunta): Unit ={
    def print_Alternativas(alternativa : List[(String,Boolean)], count : Int): Unit = {
      alternativa match {
        case x :: t => {
          if (x._2 == true) {
            println(x._1)
            if (count == 0)
              print_Alternativas(t, count)
          }else{
            if(count == 0){
              println(x._1)
              print_Alternativas(t,1)
            }
            else
              print_Alternativas(t,count)
          }
        }
      }
    }
    print_Alternativas(pergunta.alternativas,0)
  }

}
