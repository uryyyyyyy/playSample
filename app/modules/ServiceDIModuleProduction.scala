package modules

import com.google.inject.AbstractModule
import service.impl.WSServiceImpl
import service.spec.WSService

class ServiceDIModuleProduction extends AbstractModule {
  def configure() = {

    bind(classOf[WSService])
      .to(classOf[WSServiceImpl])
  }
}