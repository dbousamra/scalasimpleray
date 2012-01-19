package ray

import swing.{ Panel, MainFrame, SimpleSwingApplication }
import java.awt.{ Color, Graphics2D, Dimension }
import java.awt.event._
import java.awt
import javax.swing.Timer
import swing._
import javax.swing.filechooser.FileFilter
import javax.swing.filechooser.FileNameExtensionFilter
import scala.util.Random

object RayWindow extends SimpleSwingApplication {
  private var c: java.awt.Color = new java.awt.Color(0)
  private val frameTitle = "scalaray"

  var width: Int = 640
  var height: Int = 480
  val scene = Scene(
    eye = Vector(0, 0, 0),
    objects = List(
      Sphere(
        position = Vector(233, 290, 0),
        radius = 100,
        color = RichColor(1, 0, 0),
        diffuse = RichColor(0.0, 0.1, 0.1)),
      Sphere(
        position = Vector(407, 290, 100),
        radius = 100,
        color = RichColor(0, 1, 0),
        diffuse = RichColor(0.1, 0.0, 0.1)),
      Sphere(
        position = Vector(320, 140, 0),
        radius = 100,
        color = RichColor(0, 0, 1),
        diffuse = RichColor(0.0, 0.0, 0.1))),
    lights = List(
      Light(
        position = Vector(-400, 400, 0),
        color = RichColor(0.7, 0.7, 0.7))),
    ambientLight = Light(
      position = Vector(0, 0, 0),
      color = RichColor(0.3, 0.3, 0.3)))
  println(new SceneParser().generateScene(scene))

  val pixels = new Tracer().trace(scene, width, height)

  def top = new MainFrame {
    title = frameTitle
    contents = p
  }

  val p = new Panel {
    preferredSize = new Dimension(width, height)

    override def paintComponent(g: Graphics2D) {
      val dx = g.getClipBounds.width.toFloat / width
      val dy = g.getClipBounds.height.toFloat / height
      for {
        x <- pixels.s.indices
        y <- pixels.s(x).indices
      } {
        g.setColor(pixels.s(x)(y))
        g.fillRect(x, y, x, y)
      }
    }
  }
}