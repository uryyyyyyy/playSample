package service.spec

import javax.inject.{Inject, Singleton}

import play.api.libs.ws.WSClient

trait WSService {

  def ok(): String

}
