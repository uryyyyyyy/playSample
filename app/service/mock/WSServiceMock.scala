package service.mock

import javax.inject.{Inject, Singleton}

import model.qiita.QiitaUser
import play.api.libs.ws.WSClient
import service.spec.WSService

import scala.concurrent.Future

@Singleton
class WSServiceMock extends WSService{

  def getUsers():Future[Seq[QiitaUser]] ={
    Future.successful(Seq(QiitaUser("he", "ho")))
  }

}
