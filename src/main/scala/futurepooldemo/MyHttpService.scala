package futurepooldemo

import java.util.concurrent.{
  ExecutorService,
  Executors,
  ForkJoinPool,
  ThreadPoolExecutor
}

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response}
import com.twitter.inject.Logging
import com.twitter.inject.annotations.Flag
import com.twitter.util.{Future, FuturePool}
import javax.inject.Inject

import scala.concurrent.ExecutionContext

class MyHttpService @Inject()(
    @Flag("future_pool") futurePool: Option[FuturePool]
) extends Service[Request, Response]
    with Logging {

  private def computatingResponse(request: Request): Response = {
    val resp = Response()
    // Do some cpu intensive work
    resp.contentString = request.contentString.permutations.mkString("\n")
    resp
  }

  def apply(req: Request): Future[Response] = {
    if (req.contentString.length > 0) {
      futurePool
        .map {
          _ {
            computatingResponse(req)
          }
        }
        .getOrElse(Future(computatingResponse(req)))
    } else {
      Future(Response())
    }
  }
}
