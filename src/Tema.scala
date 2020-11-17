import Pergunta.Alternativa
//case class Tema(tema: String, perguntas: List[Pergunta]) {
//
//
//  def add_Pergunta(pergunta: Pergunta) = {
//    Tema.add_Pergunta(pergunta, this)
//  }
//}
//
//object Tema {
//  def add_Pergunta(pergunta: Pergunta, tema: Tema): Unit = {
//
//  }
//}

case class Pergunta(pergunta: String, alternativas: List[Alternativa]) {

  def receive_answer(alternativa: Alternativa): Boolean = Pergunta.receive_answer(alternativa, this.alternativas)
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

//  def main(args: Array[String]): Unit = {
//    val alternativas = List(("Não", false),("Talvez", false),("Sim", true))
//    val pergunta: Pergunta = ("O Miguel é gay?", alternativas)
//    val x : Boolean = pergunta.receive_answer(("Sim", true))
//    val y : Boolean = pergunta.receive_answer(("Sim", false))
//    println(pergunta.pergunta + x)
//    println(pergunta.pergunta + y)
//  }
}




//case class Tema(tema: String, perguntas: List[Pergunta]) {
////  type tema = tema
////  type perguntas = perguntas
//
//  def add_Pergunta(pergunta: Pergunta): Unit = {
//    perguntas :+ pergunta
//  }
//}
//
//object Tema{
//
//}
//
//
//
//case class Pergunta(pergunta: String, alternativas: List[(String, Boolean)]) {
////  type pergunta = pergunta
////  type alternativas = alternativas
//
//  def sort_Alternativas(): Unit = {
//
//  }
//
//  def selected_alternativa(alternativa: (String, Boolean)): Boolean = {
//    alternativas match {
//      case x :: t => {
//        if (x == alternativa)
//          if (x._2 == false)
//            false
//          else
//            true
//      }false
//    }
//  }
//}

