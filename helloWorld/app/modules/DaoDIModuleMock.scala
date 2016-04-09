package modules

import com.google.inject.AbstractModule
import dao.mock.QiitaClientMock
import dao.spec.QiitaClient

class DaoDIModuleMock extends AbstractModule {
  def configure() = {

    bind(classOf[QiitaClient])
      .to(classOf[QiitaClientMock])
  }
}