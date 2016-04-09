package modules

import com.google.inject.AbstractModule
import service.mock.WSServiceMock
import service.spec.WSService

class ServiceDIModuleMock extends AbstractModule {
  def configure() = {

    bind(classOf[WSService])
      .to(classOf[WSServiceMock])
  }
}