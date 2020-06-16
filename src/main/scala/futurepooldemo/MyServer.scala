package futurepooldemo

import java.util.concurrent.{Executors, ForkJoinPool, ThreadPoolExecutor}

import com.twitter.finagle.Http
import com.twitter.util.{Await, FuturePool}

object MyServer extends com.twitter.app.App {
  val futurePoolFlag = flag("futurePool", "", "Defines the future pool to use")
  val portFlag = flag("port", 8080, "Port to run server on")

  premain {
    val io_executor: ThreadPoolExecutor =
      Executors.newCachedThreadPool().asInstanceOf[ThreadPoolExecutor]
    val compute_executor: ForkJoinPool =
      Executors.newWorkStealingPool().asInstanceOf[ForkJoinPool]

    def computeFuturePool: FuturePool = FuturePool(compute_executor)
    def ioFuturePool: FuturePool = FuturePool(io_executor)

    val futurePool: Option[FuturePool] = futurePoolFlag() match {
      case "compute" => Some(computeFuturePool)
      case "io"      => Some(ioFuturePool)
      case _         => None
    }

    val server = Http.serve(s":${portFlag()}", new MyHttpService(futurePool))
    Await.ready(server)
  }

}
