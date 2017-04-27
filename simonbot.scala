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
      "🏀 Basketball trivia 🏀" -> { () => playSportTrivia() },
      "😎 Emojigame 😎" -> { () => playwithEmoji() },
      "❓ Which Pokemon are you ❓" -> { () => startPersonalityQuiz() },
      "💻 See my code 💻" -> { () => seeMyCode() }
    )
  }

  def playSportTrivia() = {
    val triviaQuestion = chooseOneAtRandom(triviaQuestions)
    sendQuestion(triviaQuestion)
  }

  def playwithEmoji() = {
    val emojiGameQuestion = chooseOneAtRandom(emojigame)
    sendQuestion(emojiGameQuestion)
  }

  def startPersonalityQuiz() = {
    sendPersonalityQuestion(PersonalityQuestions.head)
  }
   
  def seeMyCode() = {
    sendText("Click this link to see my code https://github.com/sarerics/simonbot/blob/master/simonbot.scala")
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
      sendText("Hi, I'm Simon Bot!")
      sendMenu
    }

    else if (userMessageText == "Bye") {
      sendText("Okay bye!")
      sendMenu
    }

    else if (userMessageText == "#menu") {
      sendMenu
    }

    else {
      sendWaitWhat
    }
  }

  def sendWaitWhat() = {
    sendImage("852331127570259968")
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
      "your streak is over 🌧"
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
      "how many points did Simon make in basketball ?" -> "84",
      "What's Simon's favorite sport?" -> "basketball",
      "What's Stephan Curry's number?" -> "30"
    )
  }

  val emojigame = {
    Map(
      "🌞👓" -> "Sunglasses",
      "🍔👑" -> "Burger King",
      "☕️💔" -> "Coffee Break",
      "🌟🐟" -> "Starfish",
      "🍎📺" -> "Apple TV"
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
    sendText("Correct! 🤓")
    sendText(printscore)
    sendMenu
  }

  def replyToWrongAnswer(correctAnswer: String) = {
    score = 0
    val wrongAnswerMessage = chooseOneAtRandom(wrongAnswerMessages)
    sendText(wrongAnswerMessage + correctAnswer)
    sendText(printscore)
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

  /////////////////////////////////////
  /////
  /////
  ///// Personality Quiz
  /////
  /////
  /////////////////////////////////////

  // PERSONALITIES

  val Pikachu = Personality(
    name = "Pikachu",
    imageId = "854497357454483456",
    bio = "You're very social. You are friends with everyone and a natural leader."
  )

  val Squirtle = Personality(
    name = "Squirtle",
    imageId = "854591972681449472",
    bio = "You are sensitive and thoughtful. You like to spend time by yourself or with a small group."
  )

  val Turtwig= Personality(
    name = "Turtwig",
    imageId = "854827449137246209",
    bio = "You like to hike or spend time outdoors."
  )

  val Chimchar = Personality(
    name = "Chimchar",
    imageId = "854829189844705280",
    bio = "You like to sit by the fire place alone. Very adventurest."
  )

  val Personalities = Seq(
    Pikachu,
    Squirtle,
    Turtwig,
    Chimchar
  )

  // QUESTIONS

 val Happyplace = PersonalityQuestion(
    question = "What's your happy place?",
    buttons = Map(
      "somewhere peaceful" -> Pikachu,
      "the beach" -> Squirtle,
      "the forest"->Turtwig,
      "alone by the fireplace "->Chimchar
    )
  )

   val Whatdoliketodoonyourfreetime = PersonalityQuestion(
    question = "what do like to do on your free time",
    buttons = Map(
      "handing out with friends" -> Pikachu,
      "chilling" -> Squirtle,
      "hikeing" -> Turtwig,
      "eating" -> Chimchar
    )
  )

  val FavoriteFood = PersonalityQuestion(
    question = "What's your favorite food?",
    buttons = Map(
      "Cheese 🧀" -> Pikachu,
      "Water 💧" -> Squirtle,
      "vegetables 🥗" -> Turtwig,
      "bananas 🍌" -> Chimchar
    )
  )

  val FavoriteEmoji = PersonalityQuestion(
    question = "What's your favorite emoji?",
    buttons = Map(
      "🤗⚡️" -> Pikachu,
      "🐢💧" -> Squirtle,
      "🌞🌱" -> Turtwig,
      "🐒🔥" -> Chimchar
    )
  )

  val PersonalityQuestions = Seq(
    Happyplace,
    Whatdoliketodoonyourfreetime,
    FavoriteFood,
    FavoriteEmoji
  )

  // PRINT

  def printPersonalityQuizResult(personality: Personality) = {
    sendImage(personality.imageId)
    sendText("You are " + personality.name + "!")
    sendText(personality.bio)
  }
}
