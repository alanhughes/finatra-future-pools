Example server for demonstrating different thread pooling strategies with a finagle server

Running
```
sbt "runMain futurepooldemo.MyServer -futurePool compute"
```
will launch a server on port 8080 using a bounded `WorkStealingThreadPool` executor for the "intensive" computation. Alternatively using `-futurePool io` to use an unbounded `CachedThreadPool`. Leaving the flag unset will make the intensive computation share the default Finagle threadpool.

Use the `-port` flag to set the port if you wish to run multiple servers.

Use the python scripts to perform benchmarking - a couple of example images can be seen in the `benchmarking_images` folder
