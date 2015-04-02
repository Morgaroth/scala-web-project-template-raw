## Widok project template

g8 template for some scala web project, that is build from backend server providing some http api
and some javascript application that consumes this api, written in Scala.
It is build from three sub-projects:
* codebase - common code
* back - backend application based on Akka HTTP
* front - frontend application based on Widok, scala compiled to JavaScript

## Used libraries and frameworks

* Akka HTTP - powerful HTTP library for scala build on top of Akka [Akka](http://akka.io) [Akka HTTP doc](http://doc.akka.io/docs/akka-stream-and-http-experimental/current/scala/http/index.html)
* Widok - web application framework for Scala.js [Project Site](http://widok.github.io/) [Project documentation](http://widok.github.io/manual/widok-v0.2.html)
    * Widok make use of Scala.js - Scala compiler do JavaScript [Project site](http://www.scala-js.org/)

## Usage

Install g8 if You haven't it yet and then get template:

```bash
g8 Morgaroth/scala-web-project-template-raw
```


## Todo

* add some code
* add more documentation
