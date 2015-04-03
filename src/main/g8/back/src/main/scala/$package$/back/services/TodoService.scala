package $package$.back.services

import $package$.back.protocols
import $package$.codebase.{TodoPost, Todo}
import $package$.codebase.actors.TodoRepository.messages.GetTodos
import akka.actor.ActorRef
import akka.http.marshallers.sprayjson.SprayJsonSupport
import akka.http.marshalling.Marshaller._
import akka.http.marshalling.ToResponseMarshallable
import akka.http.model.StatusCodes._
import akka.http.server.Directives
import akka.pattern.ask
import akka.stream.FlowMaterializer
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success, Try}

class TodoService(repository: ActorRef)(
  implicit exCon: ExecutionContext,
  FlowMaterializer: FlowMaterializer
  )
  extends Directives with protocols with SprayJsonSupport {

  implicit val timeout: Timeout = 3 minutes

  override val pathEnd = pathEndOrSingleSlash

  //@formatter:of
  val routes = {
    pathEnd {
      get(complete {
        (repository ? GetTodos).mapTo[Try[Set[Todo]]].map[ToResponseMarshallable] {
          case Success(todos) => todos
          case Failure(t) => BadRequest -> t.getMessage
        }
      }) ~
        (post & entity(as[TodoPost])) { posted =>
          complete(OK -> "ds")
        }
    }
  }
  //@formatter:on
}