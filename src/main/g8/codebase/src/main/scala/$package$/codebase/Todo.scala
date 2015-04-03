package $package$.codebase

import java.util.UUID

import org.joda.time.DateTime

case class TodoPost(text: String, dueDate: DateTime, createdAt: DateTime)

case class Todo(text: String, dueDate: DateTime, createdAt: DateTime, id: String)

object Todo {
  def fromPost(post: TodoPost) = Todo(post.text, post.dueDate, post.createdAt, UUID.randomUUID().toString)
}

