giter8Settings

resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)

G8Keys.g8TestBufferLog := false

scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
  a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
)

// libraries for easy development of template in IDE
libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "1.8.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.9"
)