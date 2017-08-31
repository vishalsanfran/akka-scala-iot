package com.lightbend.akka.sample

import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe}
import Greeter._
import Printer._
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}
import scala.concurrent.duration._

class AkkaHelloWorldSpec(_system: ActorSystem) extends TestKit(_system)
  with Matchers with FlatSpecLike with BeforeAndAfterAll {

  def this() = this(ActorSystem("akkaHelloWorldSpec"))

  override def afterAll(): Unit = {
    shutdown(system)
  }

  "Greeter actor" should "pass greeting msg" in {
    val testProbe = TestProbe()
    val hiGreetingMsg = "Hi There"
    val hiGreeter = system.actorOf(Greeter.props(hiGreetingMsg, testProbe.ref))
    val greetTo = "Planet"
    hiGreeter ! WhoToGreet(greetTo)
    hiGreeter ! Greet
    testProbe.expectMsg(500 millis, Greeting(s"$hiGreetingMsg, $greetTo"))
  }

}
