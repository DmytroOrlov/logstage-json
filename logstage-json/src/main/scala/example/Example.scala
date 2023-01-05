package example

import distage.{ Tag, _ }
import izumi.distage.framework.services.ConfigLoader
import izumi.distage.roles.launcher.EarlyLoggers
import izumi.fundamentals.platform.cli.model.raw.RawAppArgs
import izumi.logstage.api.{ IzLogger, Log }
import izumi.logstage.distage.LogIO3Module
import logstage.LogZIO.log
import zio._

object Example extends App {
  def run(args: List[String]) = {
    def provideHas[R: HasConstructor, A: Tag](fn: R => A): Functoid[A] =
      HasConstructor[R].map(fn)

    val program =
      for {
        _ <- log.info("123")
      } yield ()

    val module = new ModuleDef {
      make[IzLogger].from {
        val earlyLogger = IzLogger()
        EarlyLoggers.makeLateLogger(
          RawAppArgs.empty,
          earlyLogger,
          new ConfigLoader.LocalFSImpl(
            earlyLogger,
            ConfigLoader.ConfigLocation.Default,
            ConfigLoader.Args.empty,
          ).loadConfig(),
          Log.Level.Info,
          true, // change here or in config
        )
      }
      make[UIO[Unit]].from(provideHas(program.provide))
    }

    Injector[Task]()
      .produceGet[UIO[Unit]](List(module, LogIO3Module[ZIO]()).merge)
      .useEffect
      .exitCode
  }
}
