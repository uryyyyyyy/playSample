package controllers.async

import java.io.{PrintWriter, File}

import akka.actor.Actor
import model.MyObj

class FileActor extends Actor {

  def receive = {
    case name: String => {
      val tempFile = File.createTempFile("prefix_", ".csv")
      val file = new PrintWriter(tempFile)
      file.write("１行目\n")
      file.write("２行目\n")
      file.write("３行目\n")
      file.close()
      sender() ! tempFile
    }

    case obj: MyObj => {
      sender() ! MyObj(obj.id, obj.name + " super")
    }
  }
}