package com.lightbend.akka.sample

import akka.actor.{ActorRef, Props, Actor}

//companion object defines msgs handled by Greeter actor
object Greeter {
  def props(message: String, printerActor: ActorRef): Props = Props(new Greeter(message, printerActor))
  final case class WhoToGreet(who: String)
  case object Greet
}


//greeter actor
class Greeter(message: String, printerActor: ActorRef) extends Actor {
  import Greeter._

  var greeting = ""

  def receive = {
    case WhoToGreet(who) =>
      greeting = s"$message, $who"
    case Greet =>
      printerActor ! Greeting(greeting)
  }
}
