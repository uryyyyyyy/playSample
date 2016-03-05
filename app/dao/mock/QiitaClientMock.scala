package dao.mock

import javax.inject.Singleton

import dao.spec.QiitaClient
import model.qiita.QiitaUser

import scala.concurrent.Future

@Singleton
class QiitaClientMock extends QiitaClient{

  def getUsers():Future[Seq[QiitaUser]] ={
    Future.successful(Seq(QiitaUser("he", "ho")))
  }

}
