
/** Default settings to be used by all project (sub-projects) */
val defaultSettings = Seq(
        libraryDependencies ++= Seq( ), 
        scalaVersion := "2.11.5",
        scalacOptions += "-feature",
        shellPrompt := { state =>
           ShellPromptColor + s"[${Project.extract(state).currentRef.project}]> " + scala.Console.RESET
        },
        initialize ~= { _ => makeColorConsole() }
    )
    
/** Uber Jar related settings */    
val defaultAssemblySettings = Seq(
    test in assembly := {},
    assemblyOption in assembly ~= { _.copy(includeScala = false) },
    assemblyMergeStrategy in assembly := { // to Exclude spesific files
        case PathList("lib", "static", "Windows", xs @ _*) => MergeStrategy.discard
        case _ => MergeStrategy.first
    }
)

/** Setting to make console and prompt more pretty */
val ShellPromptColor = scala.Console.CYAN

def makeColorConsole() = {
   val ansi = System.getProperty("sbt.log.noformat", "false") != "true"
   if (ansi) System.setProperty("scala.color", "true")
}

/** Root Project **/    
lazy val root = (project in file(".")).enablePlugins(BuildInfoPlugin).
    settings(
        defaultSettings,
        defaultAssemblySettings,
        initialCommands in console := """ """,
        name := "ProjectName",
        organization := "io.project",
        version := "0.1",
        buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
        buildInfoPackage := organization.value + ".buildinfo"
    ) 
