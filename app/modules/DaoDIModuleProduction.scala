package modules

import com.google.inject.AbstractModule
import dao.impl.QiitaClientImpl
import dao.spec.QiitaClient

class DaoDIModuleProduction extends AbstractModule {
  def configure() = {

    bind(classOf[QiitaClient])
      .to(classOf[QiitaClientImpl])
  }
}