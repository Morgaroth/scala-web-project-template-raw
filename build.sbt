giter8Settings

resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)

G8Keys.g8TestBufferLog := false

scriptedLaunchOpts ++= sys.process.javaVmArguments.filter(
  a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
)

scalaVersion := "2.11.6"

// libraries for easy development of template in IDE
libraryDependencies ++= Seq(
  "com.github.nscala-time" %% "nscala-time" % "1.8.0",
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0-M5",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0-M5",
  "io.github.morgaroth" %% "akka-http-joda-time" % "0.1.0",
  "io.github.morgaroth" %% "akka-http-exception" % "0.1.1"
)

unmanagedSourceDirectories in Compile ++= Seq(
  sourceDirectory.value / "g8" / "back" / "src" / "main" / "scala",
  sourceDirectory.value / "g8" / "codebase" / "src" / "main" / "scala",
  sourceDirectory.value / "g8" / "front" / "src" / "main" / "scala"
)