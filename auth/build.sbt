name := """playAuth"""

version := "1.0"

libraryDependencies ++= Seq(
	jdbc,
	cache,
	ws,
	"jp.t2v" %% "stackable-controller" % "0.6.0",
	"jp.t2v" %% "play2-auth"        % "0.14.2",
	"jp.t2v" %% "play2-auth-social" % "0.14.2", // for social login
	"jp.t2v" %% "play2-auth-test"   % "0.14.2" % "test",
	play.sbt.Play.autoImport.cache,
	specs2 % Test
)

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

