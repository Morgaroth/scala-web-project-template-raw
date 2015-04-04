package $package$.back.services

import $package$.back.protocols
import $package$.codebase.{TodoPost, Todo}
import $package$.codebase.actors.TodoRepository.messages._
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

  //@formatter:off
  val routes = {
    pathEndOrSingleSlash {
      get(complete {
        (repository ? GetTodos).mapTo[Try[List[Todo]]].map[ToResponseMarshallable] {
          case Success(todos) => todos
          case Failure(t) => BadRequest -> t
        }
      }) ~
      (post & entity(as[TodoPost])) { posted =>
        complete {
          (repository ? PushTodo(Todo.fromPost(posted))).mapTo[Try[Todo]]
        }
      }
    } ~
    path(Segment) { id =>
      get(complete((repository ? GetTodo(id)).mapTo[Try[Option[Todo]]].map[ToResponseMarshallable] {
        case Success(Some(elem)) => elem
        case Success(None) => NotFound
        case Failure(t) => BadRequest -> t
      })) ~
      delete(complete((repository ? PullTodo(id)).mapTo[Try[PullResult]].map[ToResponseMarshallable] {
        case Success(PullResult(Some(_))) => NoContent
        case Success(PullResult(None)) => NotFound
        case Failure(t)=> BadRequest -> t
      }))
    }
  }
  //@formatter:on
}