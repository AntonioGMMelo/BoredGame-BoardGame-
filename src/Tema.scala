case class Tema(tema: String, perguntas: List[Pergunta]) {


  def add_Pergunta(pergunta: Pergunta): List[Pergunta] = {
    Tema.add_Pergunta(pergunta, this)
  }

  def select_Pergunta(): Pergunta = {
    Tema.select_Pergunta(this)
  }
}

object Tema {
  def add_Pergunta(pergunta: Pergunta, tema : Tema): List[Pergunta] = {
    def check_pergunta(perguntas: List[Pergunta]): List[Pergunta] = {
      perguntas match {
        case Nil => {
          println("tema.perguntas :+ pergunta")
          tema.perguntas :+ pergunta
        }
        case x :: t => {
          if (x.pergunta == pergunta.pergunta) {
            println("Essa pergunta já existe!")
            tema.perguntas
          } else
            check_pergunta(t)
        }
      }
    }
    check_pergunta(tema.perguntas)
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






