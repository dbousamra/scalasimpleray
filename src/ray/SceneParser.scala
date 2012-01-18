package ray
import scala.io.Source
import com.codahale.jerkson.Json._

case class Scene (eye: Position, objects: List[Object], lights: List[Light])
case class Position(x: Int, y: Int, z: Int)
case class Object(position: Position, radius: Int)
case class Light(position: Position)

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
     val scene = new Scene(
         eye     = new Position(0, 0, 0),
         objects = List(Object(Position(1, 2, 3), 10), Object(Position(4, 5, 6), 50)),
         lights  = List(Light(Position(7, 7, 7)), Light(Position(9, 4, 2)))
     )
     val jsonFromScene = sceneParser.generateScene(scene)
     val sceneFromJSON = sceneParser.parseScene("scene.json")
     println(sceneFromJSON)
  }
}