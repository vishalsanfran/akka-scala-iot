package com.lightbend.akka.sample

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

//companion object defines msgs handled by Greeter actor
object Greeter {
  def props(message: String, printerActor: ActorRef): Props = Props(new Greeter(message, printerActor))
  final case class WhoToGreet(who: String)
  case object Greet
}


//greeter actor
class Greeter(message: String, printerActor: ActorRef) extends Actor {
  import Greeter._
  import Printer._

  var greeting = ""

  def receive = {
    case WhoToGreet(who) =>
      greeting = s"$message, $who"
    case Greet =>
      printerActor ! Greeting(greeting)
  }
}

//printer actor comp obj expects msg of type greeting
object Printer {
  def props: Props = Props[Printer]
  final case class Greeting(greeting: String)
}

//printer actor
class Printer extends Actor with ActorLogging {
  import Printer._

  def receive: Receive = {
    case Greeting(greeting) =>
      log.info(s"Greeting received (from ${sender()}): $greeting")
  }
}