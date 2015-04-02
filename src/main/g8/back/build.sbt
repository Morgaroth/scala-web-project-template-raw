name := """$name$-server"""

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % "$akka_http_version$"
)