
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

unmanagedBase := baseDirectory.value / "libs"

lazy val buildSettings = Seq(
  scalaVersion := "2.11.8",
  organization := "special",
  javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),
  scalacOptions ++= Seq(
    "-encoding", "UTF-8",
    "-unchecked",
    "-deprecation",
    "-Xlint",
    "-feature",
    "-Ywarn-adapted-args",
    "-Ywarn-inaccessible",
    "-Ywarn-nullary-override",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:existentials",
    "-language:experimental.macros"),
  publishTo := {
    val nexus = "http://10.122.85.37:9081/nexus/"
    if (version.value.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at (nexus + "content/repositories/snapshots"))
    else
      Some("releases" at (nexus + "content/repositories/releases"))
  },
  // do not publish docs for snapshot versions
  publishArtifact in(Compile, packageDoc) := !version.value.trim.endsWith("SNAPSHOT"))

lazy val testSettings = Seq(
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.6" % Test,
    "ch.qos.logback" % "logback-classic" % "1.1.7",
    // TODO separate benchmark configuration, see https://github.com/scalameter/scalameter-examples/blob/master/basic-with-separate-config/build.sbt
    "com.storm-enroute" %% "scalameter" % "0.6" % Test),
  parallelExecution in Test := false,
  baseDirectory in Test := file("."),
  publishArtifact in Test := true,
  publishArtifact in(Test, packageSrc) := true,
  publishArtifact in(Test, packageDoc) := false,
  test in assembly := {})

lazy val commonSettings = buildSettings ++ testSettings
def libraryDefSettings = commonSettings ++ Seq(
  scalacOptions ++= Seq(
    s"-Xplugin:${file(".").absolutePath }/scalanizer/target/scala-2.11/scalanizer-assembly-0.3.0-SNAPSHOT.jar"
  )
)

lazy val allConfigDependency = "compile->compile;test->test"
cancelable in Global := true

val paradise = "org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full
val common = "com.huawei.scalan" %% "common" % "0.3.0-SNAPSHOT"
val meta = "com.huawei.scalan" %% "meta" % "0.3.0-SNAPSHOT"
val plugin = "com.huawei.scalan" %% "plugin" % "0.3.0-SNAPSHOT"
val libraryapi = "com.huawei.scalan" %% "library-api" % "0.3.0-SNAPSHOT"
val libraryimpl = "com.huawei.scalan" %% "library-impl" % "0.3.0-SNAPSHOT"
val libraryconf = "com.huawei.scalan" %% "library-conf" % "0.3.0-SNAPSHOT"

lazy val smartconf = Project("smart-conf", file("smart-conf"))
    .settings(commonSettings,
      libraryDependencies ++= Seq(
        plugin, libraryconf
      ))

lazy val scalanizer = Project("scalanizer", file("scalanizer"))
    .dependsOn(smartconf)
    .settings(commonSettings,
      libraryDependencies ++= Seq(meta, plugin, libraryapi),
      publishArtifact in(Compile, packageBin) := false,
      assemblyOption in assembly ~= { _.copy(includeScala = false, includeDependency = true) },
      artifact in(Compile, assembly) := {
        val art = (artifact in(Compile, assembly)).value
        art.copy(classifier = Some("assembly"))
      },
      addArtifact(artifact in(Compile, assembly), assembly)
    )

lazy val smartapi = Project("smart-api", file("smart-api"))
    .settings(libraryDefSettings :+ addCompilerPlugin(paradise),
      libraryDependencies ++= Seq(
        common % allConfigDependency, meta, libraryapi,
        "org.typelevel" %% "macro-compat" % "1.1.1"
      ))

lazy val root = Project("smart", file("."))
    .aggregate(smartapi, smartconf, scalanizer)
    .settings(buildSettings, publishArtifact := false)


