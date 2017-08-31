package com.lightbend.akka.sample

import akka.actor.{ActorRef, Props}

//companion object defines msgs handled by Greeter actor
object Greeter {
  def props(message: String, printerActor: ActorRef): Props = Props(new Greeter(message, printerActor))
  final case class WhoToGreet(who: String)
  case object Greet
}


class AkkaHelloWorld {

}
