package $package$.back

import $package$.back.services.TodoService
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.Http
import akka.http.server.Directives._
import akka.stream._
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor

trait HasActorSystem {
  implicit def actorSystem: ActorSystem
}

trait HttpConf {
  def conf = ConfigFactory.load().getConfig("http")
}

trait Service extends HasActorSystem with actors {
  implicit def executor: ExecutionContextExecutor

  implicit val materializer: FlowMaterializer

  val logger: LoggingAdapter

  lazy val todoService = new TodoService(repository)

  //@formatter:off
  lazy val routes = {
    logRequestResult("$name$-http") {
      pathEndOrSingleSlash {
        complete("")
      } ~
      path("todo")(todoService.routes)
    }
  }
  //@formatter:on
}

object RESTApplication extends App with Service with HttpConf {
  override implicit val actorSystem = ActorSystem()
  override implicit val executor = actorSystem.dispatcher
  override implicit val materializer = ActorFlowMaterializer()

  override val logger = Logging(actorSystem, getClass)

  Http().bindAndHandle(routes, conf.getString("interface"), conf.getInt("port"))
}