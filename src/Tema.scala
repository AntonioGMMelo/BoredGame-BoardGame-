case class Tema(tema : String, perguntas : List[Pergunta]){

}

case class Pergunta(pergunta : String, alternativas : List[(String, Boolean)]){

}

object Temas{

}
