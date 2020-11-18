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


<<<<<<< HEAD
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

<<<<<<< HEAD
//  def main(args: Array[String]): Unit = {
//    val alternativas = List(("Não", false),("Talvez", false),("Sim", true))
//    val pergunta: Pergunta = ("O Miguel é gay?", alternativas)
//    val x : Boolean = pergunta.receive_answer(("Sim", true))
//    val y : Boolean = pergunta.receive_answer(("Sim", false))
//    println(pergunta.pergunta + x)
//    println(pergunta.pergunta + y)
//  }
=======
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
      pergunta.alternativas match {
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
//    val pergunta_X : Pergunta = esporte.select_Pergunta()
//    val pergunta_X = esporte.perguntas(1)
    println("Pergunta_X:")
    a.print_Pergunta()
    println("Dica pergunta_X: ")
//    a.tip_Alternativas()
    println("a")
  }
}





