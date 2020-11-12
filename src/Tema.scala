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