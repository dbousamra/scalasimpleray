package ray
import scala.io.Source
import com.codahale.jerkson.Json._

class SceneParser {
  def parseScene(file: String) = {
    val raw = Source.fromFile(file).mkString
    parse[Scene](raw)
  }
}