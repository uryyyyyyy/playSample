package service.impl

import javax.inject.{Inject, Singleton}

import dao.spec.QiitaClient
import model.qiita.QiitaUser
import service.spec.WSService

import scala.concurrent.Future

@Singleton
class WSServiceImpl @Inject()(client: QiitaClient) extends WSService{

  def getUsers():Future[Seq[QiitaUser]] ={
    client.getUsers()
  }

}
