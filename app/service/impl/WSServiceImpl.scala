package service.impl

import javax.inject.{Inject, Singleton}

import play.api.libs.ws.WSClient
import service.spec.WSService

@Singleton
class WSServiceImpl @Inject()(ws: WSClient) extends WSService{

  def ok():String ={
    ws.toString
  }

}
