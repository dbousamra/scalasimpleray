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

  var width: Int = 320
  var height: Int = 240
  val scene = new SceneParser().parseScene("scene.json")
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