package modules

import com.google.inject.AbstractModule
import service.impl.WSServiceImpl
import service.mock.WSServiceMock
import service.spec.WSService

class MockDIModule extends AbstractModule {
  def configure() = {

    bind(classOf[WSService])
      .to(classOf[WSServiceMock])
  }
}