BORED GAME
A scala and functional programming project.
requirements: JDK 15, scala SBT 1.3.13 http://scala-sbt.org/download.html,JavaFX 14.0.2.1 https://gluonhq.com/products/javafx/ and
Scala interpreter ("REPL") from:https://downloads.lightbend.com/scala/2.13.3/scala-2.13.3.msi
IDE used: InteliJ with scala extension
Main Function in file GUI.scala under the name Menu

RULES:
1-This Game´s objective is to reach the ending before everyone else by answering diferent question types i.e. questions similar to the american gameshows Family Feud and the Price is Right also regular trivia questions with multiple choice answers.
2-2-8 players needed to start.
3-Main Menu Options include adding your own questions of each type and deleting any question you desire you are also allowed to "Create Player" and "Edit Player" just by chosing a color but keep in mind that the order in wich the players are created is also the order of play.
4-Once you have a minimum of 2 players you can start the game each player in each turn rolls a dice(each player also starts wiht the option to reroll the dice once a game) and moves that rolls amount of spaces in the board, there is also a "loaded" dice which can only be acessed by drawing a card this particular dice can be thrown only once when the wheel lands on "Roll Weighted Dice" and it only has 6 faces with the amounts 2,4 and 6 twice each.
5-If the space where the player landed is a multiple of three (meaning 3,6,9...) the player then draws a card (with a once a game option to redraw) if the space is even but not a multiple of three (meaning 2,4,8...) the player then spins the wheel if the space is not a multiple of 3 and is odd the player then answers a regular multiple choice trivia question, the spaces are numbered from 1-40 and then 39-1 clockwisethe start/finish space is space number 0(80 spaces in total) .
6-In case the player answers the regular multiple choice correctly they then move up another space if they get it wrong they move back a space,while answering the regular multiple choice question the players start with three powerups that they can re-earn by drawing a card these power ups are "Get Right" which simply means you don't answer the question but still move up a space, "50/50" which reduces the alternatives to only two and "Skip Question" which swaps the question for another one.
7-Possible WheelSpins are; "Move Forward X spaces","Move Back X spaces","All Players Move Forward X Spaces","All Players Move Back X Spaces","Stay","Roll the Dice","Roll the Weighted Dice" which are self explanatory and "Go to Jail" which means the player loses their next turn and most inportantly "Price Aint Right Round" which is a diferent type of questions where ALL player in the same order of play give a decimal value(x.y like 15.0) to an item like Chocolate or Ruller and the player that is closest moves forward 10 spaces.
8-Possible card draws are;"Roll the Dice","Roll the Weighted Dice","Go to Jail"(Same as wheelspins),"50/50","Skip Question","Get Right"(if you did not have these powerups previously and draw their card then you get another use) and most importantly "Feud Round" which is another type of question where there are 5 possible answers and 1 worth 3 spaces,2 worth 1 space and 2 worth 1 space it is an open question like "What was the first Starwars" for this reason there is no punishment for getting the question wrong lets say the answers to this question were "4,5,6,1,2" if you typed "4" you would have moved forward 3 spaces, "5" or "6" 2 spaces, "1" or "2" one space any other answer would not warrant any movement.
9-The winner is prompted for a quote.
10-Now that you know the rules you are ready to play, HAVE FUN!
OBSERVATIONS:
WHILE "CLOSE GAME" MERELY CLOSES THE WINDOW "END GAME" RESTARTS THE GAME AND CLOSES THE WINDOW!!!
