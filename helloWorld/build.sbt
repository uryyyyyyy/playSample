name := """playHelloWorld"""

version := "1.0"

libraryDependencies ++= Seq(
	jdbc,
	cache,
	ws,
	specs2 % Test
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

