package com.lightbend.akka.sample

import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

class AkkaHelloWorldSpec(system: ActorSystem) extends TestKit(system)
  with Matchers with FlatSpecLike with BeforeAndAfterAll {

  "Greeter actor" should "pass greeting msg" in {
    val testProbe = TestProbe()
    val hiGreetingMsg = "hi"
    val hiGreeter = system.actorOf(Greeter.props(hiGreetingMsg, testProbe.ref))
  }

}
