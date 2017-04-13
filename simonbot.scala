package com.twitter.mybot.api.simonbot

/////////////////////////////////////
/////
/////
///// @SimonBot30 is a Twitter DM Bot built by a middle school student named Simon
/////
/////
///// http://twitter.com/messages/compose?recipient_id=839333934223646720
/////
/////
/////////////////////////////////////

trait SimonBot extends SimonBotPlus {

  /////////////////////////////////////
  /////
  /////
  ///// Menu Buttons
  /////
  /////
  /////////////////////////////////////
 
  def menuMessageText() = {
    "What do you want to do?"
  }

  val menu = {
    Map(
      "Play trivia ❓" -> { () => playSportTrivia() },
      "Emojigame" -> { () => playwithEmoji() },
      "💻 See my code" -> { () => seeMyCode() }
    )
  }

  def playSportTrivia() = {
    val triviaQuestion = chooseOneAtRandom(triviaQuestions)
    sendMessageWithQuestion(triviaQuestion)
  }

  def playwithEmoji() = {
    val emojiGameQuestion = chooseOneAtRandom(emojigame)
    sendMessageWithQuestion(emojiGameQuestion)
  }
   
  def seeMyCode() = {
    sendMessageWithText("Click this link to see my code https://github.com/sarerics/simonbot/blob/master/simonbot.scala")
    sendMenu
  }

  /////////////////////////////////////
  /////
  /////
  ///// Main SimonBot logic
  /////
  /////
  ///// Read a message, then send a reply.
  /////
  /////
  /////////////////////////////////////

  def replyToMessage(userMessageText: String) = {

    if (userMessageText == "Hi") {
      sendMessageWithText("Hi Simon!")
    }

    else if (userMessageText == "Bye") {
      sendMessageWithText("Okay bye!")
    }

    else if (userMessageText == "What's Stephan Curry's number?") {
      sendMessageWithText("30")
    }

    else if (userMessageText == "What's my favorite sport?") {
      sendMessageWithText("basketball")
    }

    else if (userMessageText == "#menu") {
      // Do nothing
    }

    else {
      sendMessageWithText("https://s-media-cache-ak0.pinimg.com/236x/49/2e/67/492e677ba588b95666f335dff2678048.jpg")
    }

    sendMenu
  }

  /////////////////////////////////////
  /////
  /////
  ///// Show a message based on the user's score
  /////
  /////
  /////////////////////////////////////

  def printscore() = {
    if (score == 0 ) {
      "you're streak is over 🌧"
    }

    else if (score == 1) {
      "you're starting to get on fire🔥"
    }

    else {
      "🔥" * score
    }
  }

  /////////////////////////////////////
  /////
  /////
  ///// Pools of Questions
  /////
  /////
  /////////////////////////////////////

  val triviaQuestions = {
    Map(
      "What year did Kobe Bryant retire?" -> "2016",
      "How many championships has Michael Jordan won?" -> "6",
      "How many 3's did Curry make ?" -> "1847",
      "what's the percentage of asians in the NBA " -> "0.2",
      "how many point's did Simon make in basketball ?" -> "79"
    )
  }

  val emojigame = {
    Map(
      "🌞👓" -> "Sunglasses",
      "🍔👑" -> "Burgerking",
      "☕️💔" -> "Coffeebreack",
      "🌟🐟" -> "Starfish",
      "🍎📺" -> "Appletv"
    )
  }

  /////////////////////////////////////
  /////
  /////
  ///// Answer Responses
  /////
  /////
  /////////////////////////////////////

  def replyToCorrectAnswer() = {
    score = score + 1
    sendMessageWithText("Correct! 🤓")
    sendMessageWithText(printscore)
    sendMenu
  }

  def replyToWrongAnswer(correctAnswer: String) = {
    score = 0
    val wrongAnswerMessage = chooseOneAtRandom(wrongAnswerMessages)
    sendMessageWithText(wrongAnswerMessage + correctAnswer)
    sendMessageWithText(printscore)
    sendMenu
  }

  /////////////////////////////////////
  /////
  /////
  ///// Pool of Wrong Answer Messages
  /////
  /////
  /////////////////////////////////////

  def wrongAnswerMessages() = {
    Seq(
      "corect 🤓 Just kidding! https://www.youtube.com/watch?v=dQw4w9WgXcQ You should have said: ",
      "Wrong! 😭 The correct answer is: "
    )
  }
}
