package com.lightbend.akka.sample

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import scala.io.StdIn

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

  def receive: Receive = {
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

//main
object AkkaHelloWorld extends App {
  import Greeter._
  val system: ActorSystem = ActorSystem("vishalSystem")
  try {
    //create printer actor
    val printer: ActorRef = system.actorOf(Printer.props, "printerActor")
    val helloGreeter: ActorRef =
      system.actorOf(Greeter.props("Hello", printer), "helloGreeter")
    val howdyGreeter: ActorRef =
      system.actorOf(Greeter.props("Howdy", printer), "howdyGreeter")
    val holaGreeter: ActorRef =
      system.actorOf(Greeter.props("Hola", printer), "holaGreeter")

    //send msgs into Greeter's mailbox
    helloGreeter ! WhoToGreet("Vishal")
    helloGreeter ! Greet
    helloGreeter ! WhoToGreet("Mona")
    helloGreeter ! Greet
    howdyGreeter ! WhoToGreet("World")
    howdyGreeter ! Greet
    holaGreeter ! WhoToGreet("America")
    holaGreeter ! Greet

    println("Enter to exit")
    StdIn.readLine()
  } finally {
    system.terminate()
  }
}