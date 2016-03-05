package service.mock

import javax.inject.{Inject, Singleton}

import play.api.libs.ws.WSClient
import service.spec.WSService

@Singleton
class WSServiceMock extends WSService{

  def ok():String ={
    "dummy"
  }

}
