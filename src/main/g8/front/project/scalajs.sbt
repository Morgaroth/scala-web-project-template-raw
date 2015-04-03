import org.scalajs.sbtplugin.ScalaJSPlugin.AutoImport._

persistLauncher := true

artifactPath in(Compile, packageScalaJSLauncher) := baseDirectory.value / "web" / "js" / "launcher.js"

artifactPath in(Compile, fastOptJS) := baseDirectory.value / "web" / "js" / "application.js"

artifactPath in(Compile, fullOptJS) := baseDirectory.value / "web" / "js" / "application.min.js"

scalaJSSemantics ~= (_
  .withRuntimeClassName(_ => "")
  .withAsInstanceOfs(CheckedBehavior.Unchecked)
  )