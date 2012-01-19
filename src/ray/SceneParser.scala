package ray
import scala.io.Source
import com.codahale.jerkson.Json._
import java.awt.Color
class SceneParser {
  def parseScene(file: String) = {
    val raw = Source.fromFile(file).mkString
    parse[Scene](raw)
  }
  def generateScene(scene: Scene) = {
    generate(scene)
  }
}