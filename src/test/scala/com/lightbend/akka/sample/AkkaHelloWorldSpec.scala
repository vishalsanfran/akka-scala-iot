package com.lightbend.akka.sample

import akka.actor.ActorSystem
import akka.testkit.TestKit
import org.scalatest.{BeforeAndAfterAll, FlatSpecLike, Matchers}

class AkkaHelloWorldSpec(system: ActorSystem) extends TestKit(system)
  with Matchers with FlatSpecLike with BeforeAndAfterAll {

}
