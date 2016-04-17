package io.project.core

import io.project.buildinfo._

object Utils {
    def colorPrintScreen(toPrint: String) = 
       try { 
          println(scala.Console.YELLOW + toPrint)
       } finally {
          print(scala.Console.RESET)
       }
}

object Core extends App {
    Utils.colorPrintScreen(s"Build info: ${BuildInfo.toString}")
}
