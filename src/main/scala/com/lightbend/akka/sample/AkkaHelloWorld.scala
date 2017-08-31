package com.lightbend.akka.sample

//companion object defines msgs handled by Greeter actor
object Greeter {
  final case class WhoToGreet(who: String)
  case object Greet
}


class AkkaHelloWorld {

}
