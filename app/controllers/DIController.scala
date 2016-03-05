package controllers

import java.io.{File, PrintWriter}
import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, Controller}
import service.impl.WSServiceImpl
import service.spec.WSService

@Singleton
class DIController @Inject()(ws: WSService) extends Controller {

  def download() = Action {
    Ok(ws.ok())
  }
}