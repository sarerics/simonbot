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

object SimonBot extends SimonBotPlus {

  var score = 0

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

  def replyToMessage(message: String) = {
    if (message == "Hi") {
      sendMessageWithText("Hi Simon!")
    }

    else if (message == "Bye") {
      sendMessageWithText("Okay bye!")
    }

    else if (message == "What's Stephan Curry's number?") {
      sendMessageWithText("30")
    }

    else if (message == "What's my favorite sport?") {
      sendMessageWithText("basketball")
    }

    else if (message == "#trivia") {
      val triviaQuestion = chooseOneAtRandom(triviaQuestions)
      sendMessageWithTriviaQuestion(triviaQuestion)
    }

    else if (message == "#code") {
      sendMessageWithText("https://github.com/sarerics/simonbot/blob/master/simonbot.scala")
    }

    else {
      sendMessageWithText("https://s-media-cache-ak0.pinimg.com/236x/49/2e/67/492e677ba588b95666f335dff2678048.jpg")
    }
  }

  /////////////////////////////////////
  /////
  /////
  ///// Show a message based on the user's score
  /////
  /////
  /////////////////////////////////////

  def printscore = {
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
  ///// Pool of Trivia Questions
  /////
  /////
  /////////////////////////////////////

  def triviaQuestions = {
    Map(
      "What year did Kobe Bryant retire?" -> "2016",
      "How many championships has Michael Jordan won?" -> "6",
      "How many 3's did Curry make ?"     ->"1847",
      "what's the percentage of asians in the NBA " -> "0.2",
      "how many point's did Simon make in basketball ?" -> "79"
    )
  }

  /////////////////////////////////////
  /////
  /////
  ///// Trivia Responses
  /////
  /////
  /////////////////////////////////////

  def replyToCorrectTriviaAnswer(answer: String) = {
    score = score + 1
    sendMessageWithText("Correct! 🤓 " + printscore)
  }

  def replyToWrongTriviaAnswer(correctAnswer: String, wrongAnwer: String) = {
    score = 0
    val wrongAnswerMessage = chooseOneAtRandom(wrongAnswerMessages)
    sendMessageWithText(wrongAnswerMessage  + correctAnswer + " " + printscore)
  }

  /////////////////////////////////////
  /////
  /////
  ///// Pool of Wrong Answer Messages
  /////
  /////
  /////////////////////////////////////

  def wrongAnswerMessages = {
    Seq(
      "corect 🤓 Just kidding! https://www.youtube.com/watch?v=dQw4w9WgXcQ You should have said: ",
      "Wrong! 😭 The correct answer is: "
    )
  }
}
