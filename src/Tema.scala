<<<<<<< HEAD
case class Tema(tema: String, perguntas: List[Pergunta]) {


  def add_Pergunta(pergunta: Pergunta) = {
    Tema.add_Pergunta(pergunta, this)
  }
}

object Tema {
  def add_Pergunta(pergunta: Pergunta, tema: Tema): Unit = {
    
  }
}

case class Pergunta(pergunta: String, alternativas: List[(String, Boolean)]) {
  def create_Pergunta() = {
    Pergunta.create_Pergunta(this)
  }
}

object Pergunta {
  def create_Pergunta(): Unit = {

  }

}
=======
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
>>>>>>> d94c62b80dd1578a1c8d2f6d994fe32efa725645
