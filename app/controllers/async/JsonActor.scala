package controllers.async

import akka.actor.Actor

class JsonActor extends Actor {

  def receive = {
    case name: String =>
      sender() ! "Hello, " + name
  }
}