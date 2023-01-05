ThisBuild / scalaVersion := "2.13.10"

lazy val dependencies =
  new {
    val zio = "dev.zio" %% "zio" % "1.0.17"
    val distageFramework = "io.7mind.izumi" %% "distage-framework" % "1.1.0-M12"
  }


lazy val `logstage-json` = project
  .settings(
    libraryDependencies ++= Seq(
      dependencies.zio,
      dependencies.distageFramework,
    )
  )

lazy val `repro-root` = (project in file("."))
  .aggregate(
    `logstage-json`,
  )
