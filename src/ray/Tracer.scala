package ray
import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

class Tracer(width: Int = 640, height: Int = 480) {

  class Screen(x: Int, y: Int) {
    val s = Array.ofDim[Color](x, y)
    def apply(x: Int) = s(x)
    def update(x: Int, y: Int, value: Color) { s(x)(y) = value }
  }

  def trace(scene: Scene, width: Int, height: Int) = {
    var screen = new Screen(width, height)
    for {
      x <- screen.s.indices
      y <- screen.s(x).indices
    } {
      val ray = Ray(Vector(0, 0, 0), Vector(x - width / 2, height / 2 - y, 640).normalized)
      val d = scene.objects(0).intersectRay(ray)
      val c = if (d == Double.PositiveInfinity) 0 else Color.CYAN.getRGB() //not computing actual color. Just if it hits or misses
      screen.s(x)(y) = new Color(c)
    }
    screen
  }
}