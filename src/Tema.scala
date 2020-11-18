case class Tema(tema: String, perguntas: List[Pergunta]) {

  def add_Pergunta(pergunta: Pergunta): Tema = {
    Tema.add_Pergunta(pergunta, this)
  }

  def select_Pergunta(): Pergunta = {
    Tema.select_Pergunta(this)
  }
}

object Tema {
  def add_Pergunta(pergunta: Pergunta, tema: Tema): Tema = {
    Tema(tema.tema, new_perguntas(tema.perguntas, pergunta))
  }

  def new_perguntas(perguntas: List[Pergunta], pergunta: Pergunta): List[Pergunta] = {
    if (existe_pergunta(perguntas, pergunta))
      perguntas
    else
      perguntas :+ pergunta
  }

  def existe_pergunta(perguntas: List[Pergunta], pergunta: Pergunta): Boolean = {
    perguntas match {
      case Nil => false
      case x :: t => {
        if (x == pergunta) {
          println("Essa pergunta já existe")
          true
        } else {
          existe_pergunta(t, pergunta)
        }
      }
    }
  }

  //  def remove_pergunta(pergunta:String, perguntas : List[Pergunta]): Unit = {
  //    perguntas match {
  //      case Nil => println("Essa pergunta não existe")
  //      case x :: t => {
  //        if(x.pergunta == pergunta)
  //
  //      }
  //    }
  //  }

  def select_Pergunta(tema: Tema): Pergunta = {
    val r = new scala.util.Random
    tema.perguntas(r.nextInt(tema.perguntas.size))
  }

}






