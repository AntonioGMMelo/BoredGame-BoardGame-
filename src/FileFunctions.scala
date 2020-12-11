import java.io.{File, PrintWriter}

import Pergunta.Alternativa

import scala.io.Source
import scala.util.{Failure, Success, Try}

object FileFunctions {
  type feud = (String, List[String])
  type Color = (String, Boolean) // Color of the Player
  type item = (String, Double) //Item type
  type PlayerExtra = (String, (Int, Int), Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean, Boolean) //Player(color,(posX,posY),canMove,canReRolDice,canWeighDice,canSkipQuestion,can50/50,canAskForMoreTime,canReSpinTheWheel,canDrawNewCard)

  def makeColor(string: String):Color = {
    val split1 = string.split(";")
    (split1(0), split1(1).toBoolean)
  }

  def makeColorString(color: Color):String = {
    val aux = color._1 + ";" + color._2 + "\n"
    aux
  }
  def makePlayerString(p: PlayerExtra): String = {
    var result = p._1 + ";"
    result = result + p._2._1 + "," + p._2._1 + ";"
    result = result + p._3 + ";"
    result = result + p._4 + ";"
    result = result + p._5 + ";"
    result = result + p._6 + ";"
    result = result + p._7 + ";"
    result = result + p._8 + ";"
    result = result + p._9 + ";"
    result = result + p._10 + "\n"
    val r = result
    r
  }

  def makePlayer(string: String): PlayerExtra = {
    val split1 = string.split(";")
    val st = split1(0)
    val t = split1(1).split(",")
    val t1 = t(0).toInt
    val t2 = t(1).toInt
    val b1 = split1(2).toBoolean
    val b2 = split1(3).toBoolean
    val b3 = split1(4).toBoolean
    val b4 = split1(5).toBoolean
    val b5 = split1(6).toBoolean
    val b6 = split1(7).toBoolean
    val b7 = split1(8).toBoolean
    val b8 = split1(9).toBoolean
    (st, (t1, t2), b1, b2, b3, b4, b5, b6, b7, b8)
  }

  def makeItem(string: String): item = {
    val split1 = string.split(";")
    (split1(0), split1(1).toDouble)
  }

  def makeItemString(i: item): String = {
    val r1 = i._1
    val r2 = r1 + ";" + i._2.toString + "\n"
    r2
  }

  def makeStringPerguntas(pergunta: Pergunta): String = {
    var result: String = pergunta.pergunta
    result = result + ";"
    var a = 0
    while (a < pergunta.alternativas.length) {
      result = result + pergunta.alternativas(a)._1
      result = result + "/"
      result = result + pergunta.alternativas(a)._2
      if (a != pergunta.alternativas.length - 1)
        result = result + ","
      else
        result = result + "\n"
      a += 1
    }
    val r = result
    r
  }


  def makePergunta(string: String): Pergunta = {
    val aux = string.split(";")
    val aux2 = aux(1).split(",")

    def makeAlternativas(list: List[String]): List[Alternativa] = {
      list match {
        case Nil => Nil
        case x :: t => {
          val aux3 = x.split("/")
          if (aux3(1) == "false")
            (aux3(0), false) :: makeAlternativas(t)
          else
            (aux3(0), true) :: makeAlternativas(t)
        }
      }
    }

    Pergunta(aux(0), makeAlternativas(aux2.toList))
  }

  def makeFeud(string: String): feud = {
    val split1 = string.split(";")
    val st = split1(0)
    val split2 = split1(1).split(",")
    (st, split2.toList)
  }

  def makeFeudString(f: feud): String = {
    var result = f._1 + ";"
    var aux = 0
    while (aux < f._2.length) {
      result = result + f._2(aux)
      if (aux != f._2.length - 1)
        result = result + ","
      else
        result = result + "\n"
      aux += 1
    }
    val r = result
    r
  }

  def check_File(filename: String): Try[List[String]] = {
    Try(Source.fromFile(filename).getLines.toList)
  }

  def read_file[E](file: String, func: (String) => E): List[E] = {
    var res: List[E] = List()
    check_File(file) match {
      case Success(lines) => res = aux()
      case Failure(f) => println(f)
    }

    def aux(): List[E] = {
      val bufferedSource = Source.fromFile(file)
      for (line <- bufferedSource.getLines) {
        res = res :+ func(line)
      }
      bufferedSource.close
      res
    }
    res
  }

  def write_file[E](file: String, func: (E) => String, list: List[E]): Unit = {
    val pw = new PrintWriter(new File(file))
    var a = 0
    while (a < list.length) {
      pw.write(func(list(a)))
      a += 1
    }
    pw.close
  }

}