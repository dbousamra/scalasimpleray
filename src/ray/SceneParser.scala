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

object SceneParser {

  def main(args: Array[String]) {

    val sceneParser = new SceneParser()
    //sample scene - used to reverse engineer JSON format
    val scene = Scene(
    eye = Vector(0, 0, 0),
    objects = List(
      Sphere(
        position = Vector(0, 0, 400),
        radius = 100,
        color = RichColor(1, 1, 0))),
    lights = List(
      Light(
        position = Vector(-400, 400, 0),
        color = RichColor(0.7, 0.7, 0.7))),
    ambientLight = Light(
      position = Vector(0, 0, 0),
      color = RichColor(0.3, 0.3, 0.3)))
    val jsonFromScene = sceneParser.generateScene(scene)
    val sceneFromJSON = parse[Scene](jsonFromScene)
    println(jsonFromScene)
  }
}