package service.spec

import javax.inject.{Inject, Singleton}

import model.qiita.QiitaUser
import play.api.libs.ws.WSClient

import scala.concurrent.Future

trait WSService {

  def getUsers(): Future[Seq[QiitaUser]]

}
